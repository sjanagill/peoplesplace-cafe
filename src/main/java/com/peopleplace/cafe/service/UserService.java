package com.peopleplace.cafe.service;


import com.peopleplace.cafe.config.MessageStrings;
import com.peopleplace.cafe.dto.ResponseDto;
import com.peopleplace.cafe.dto.user.*;
import com.peopleplace.cafe.enums.ResponseStatus;
import com.peopleplace.cafe.enums.Role;
import com.peopleplace.cafe.exceptions.AuthenticationFailException;
import com.peopleplace.cafe.exceptions.CustomException;
import com.peopleplace.cafe.model.AuthenticationToken;
import com.peopleplace.cafe.model.User;
import com.peopleplace.cafe.repository.UserRepository;
import com.peopleplace.cafe.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.peopleplace.cafe.config.MessageStrings.USER_CREATED;
import static com.peopleplace.cafe.config.MessageStrings.USER_UPDATED;


/**
 *
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    AuthenticationService authenticationService;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * @param signupDto
     * @return
     * @throws CustomException
     */
    public ResponseDto signUp(SignupDto signupDto) throws CustomException {
        // Check to see if the current email address has already been registered.
        if (Helper.notNull(userRepository.findByEmail(signupDto.getEmail()))) {
            // If the email address has been registered then throw an exception.
            throw new CustomException("User already exists");
        }
        // first encrypt the password
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new CustomException(MessageStrings.HASH_FAILURE + e.getMessage());
        }


        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), Role.user, encryptedPassword);
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        User createdUser;
        try {
            // save the User
            createdUser = userRepository.save(user);
            // generate token for user
            final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
            // save token in database
            authenticationService.saveConfirmationToken(authenticationToken);
            // success in creating
            return new ResponseDto(ResponseStatus.success.toString(), authenticationToken.getToken());
        } catch (Exception e) {
            // handle signup error
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * @param signInDto
     * @return
     * @throws CustomException
     */
    public SignInResponseDto signIn(SignInDto signInDto) throws CustomException {
        // first find User by email
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (!Helper.notNull(user)) {
            throw new AuthenticationFailException("user not present");
        }
        try {
            // check if password is right
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                // passowrd doesnot match
                throw new AuthenticationFailException(MessageStrings.WRONG_P);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new CustomException(MessageStrings.HASH_FAILURE + e.getMessage());
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if (!Helper.notNull(token)) {
            // token not present
            throw new CustomException("token not present");
        }

        return new SignInResponseDto("success", token.getToken());
    }

    /**
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }

    /**
     * @param token
     * @param userCreateDto
     * @return
     * @throws CustomException
     * @throws AuthenticationFailException
     */
    public ResponseDto createUser(String token, UserCreateDto userCreateDto) throws CustomException, AuthenticationFailException {
        User creatingUser = authenticationService.getUser(token);
        if (!Helper.canCrudUser(creatingUser.getRole())) {
            // user can't create new user
            throw new AuthenticationFailException(MessageStrings.USER_NOT_PERMITTED);
        }
        String encryptedPassword = userCreateDto.getPassword();
        try {
            encryptedPassword = hashPassword(userCreateDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new CustomException(MessageStrings.HASH_FAILURE + e.getMessage());
        }

        User user = new User(userCreateDto.getFirstName(), userCreateDto.getLastName(), userCreateDto.getEmail(), userCreateDto.getRole(), encryptedPassword);
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        User createdUser;
        try {
            createdUser = userRepository.save(user);
            final AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new ResponseDto(ResponseStatus.success.toString(), USER_CREATED);
        } catch (Exception e) {
            // handle user creation fail error
            throw new CustomException(e.getMessage());
        }

    }

    /**
     * @param token
     * @param userUpdateDto
     * @return
     * @throws CustomException
     * @throws AuthenticationFailException
     */
    public ResponseDto updateUser(String token, UserUpdateDto userUpdateDto) throws CustomException, AuthenticationFailException {
        User updatingUser = authenticationService.getUser(token);
        User user;
        if (!Helper.canCrudUser(updatingUser.getRole())) {
            // user can't create new user
            throw new AuthenticationFailException(MessageStrings.USER_NOT_PERMITTED);
        }
        if (!userUpdateDto.getPassword().isEmpty()) {
            String encryptedPassword = userUpdateDto.getPassword();
            try {
                encryptedPassword = hashPassword(userUpdateDto.getPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw new CustomException(MessageStrings.HASH_FAILURE + e.getMessage());
            }
            user = new User(userUpdateDto.getFirstName(), userUpdateDto.getLastName(), userUpdateDto.getRole(), encryptedPassword);
        } else {
            user = new User(userUpdateDto.getFirstName(), userUpdateDto.getLastName(), userUpdateDto.getRole());
        }
        User updatedUser;
        try {
            updatedUser = userRepository.save(user);
            final AuthenticationToken authenticationToken = new AuthenticationToken(updatedUser);
            authenticationService.saveConfirmationToken(authenticationToken);
            return new ResponseDto(ResponseStatus.success.toString(), USER_UPDATED);
        } catch (Exception e) {
            // handle user creation fail error
            throw new CustomException(e.getMessage());
        }

    }
}
