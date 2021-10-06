package com.peopleplace.cafe.controller;


import com.peopleplace.cafe.dto.ResponseDto;
import com.peopleplace.cafe.dto.user.*;
import com.peopleplace.cafe.exceptions.AuthenticationFailException;
import com.peopleplace.cafe.exceptions.CustomException;
import com.peopleplace.cafe.model.User;
import com.peopleplace.cafe.repository.UserRepository;
import com.peopleplace.cafe.service.AuthenticationService;
import com.peopleplace.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> findAllUser(@RequestHeader("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    // TODO token should be updated
    @PostMapping("/signIn")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws CustomException {
        return userService.signIn(signInDto);
    }

    @PostMapping("/updateUser")
    public ResponseDto updateUser(@RequestHeader("token") String token, @RequestBody UserUpdateDto userUpdateDto) {
        authenticationService.authenticate(token);
        return userService.updateUser(token, userUpdateDto);
    }


    @PostMapping("/createUser")
    public ResponseDto createUser(@RequestHeader("token") String token, @RequestBody UserCreateDto userCreateDto)
            throws CustomException, AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.createUser(token, userCreateDto);
    }
}
