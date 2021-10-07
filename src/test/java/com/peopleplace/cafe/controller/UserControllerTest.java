package com.peopleplace.cafe.controller;

import com.peopleplace.cafe.PeoplesPlaceDemoApplication;
import com.peopleplace.cafe.dto.ResponseDto;
import com.peopleplace.cafe.dto.user.SignInDto;
import com.peopleplace.cafe.dto.user.SignInResponseDto;
import com.peopleplace.cafe.dto.user.SignupDto;
import com.peopleplace.cafe.dto.user.UserUpdateDto;
import com.peopleplace.cafe.enums.Role;
import com.peopleplace.cafe.model.User;
import com.peopleplace.cafe.repository.UserRepository;
import com.peopleplace.cafe.service.AuthenticationService;
import com.peopleplace.cafe.service.UserService;
import de.flapdoodle.embed.mongo.MongodExecutable;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PeoplesPlaceDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    User user;
    SignupDto signupDto;
    SignupDto signupDto2;
    SignupDto signupDto3;
    SignInDto signInDto;
    SignInDto signInDto2;
    UserUpdateDto userUpdateDto;

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;
    private MongodExecutable mongodExecutable;
    @LocalServerPort
    private int port;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        user = new User();
        user.setFirstName("FirstName");
        user.setLastName("FirstName");
        user.setEmail("fisrtname.secondname@gmail.com");
        user.setRole(Role.admin);
        user.setPassword("pa5sword");

        signupDto = new SignupDto();
        signupDto.setFirstName("FirstName");
        signupDto.setLastName("FirstName");
        signupDto.setEmail("fisrtname.secondname@gmail.com");
        signupDto.setPassword("pa5sword");

        signupDto2 = new SignupDto();
        signupDto2.setFirstName("FirstName2");
        signupDto2.setLastName("FirstName2");
        signupDto2.setEmail("fisrtname2.secondname@gmail.com");
        signupDto2.setPassword("pa5sword");

        signupDto3 = new SignupDto();
        signupDto3.setFirstName("FirstName3");
        signupDto3.setLastName("FirstName3");
        signupDto3.setEmail("fisrtname3.secondname@gmail.com");
        signupDto3.setPassword("pa5sword");

        signInDto = new SignInDto();
        signInDto.setEmail("fisrtname.secondname@gmail.com");
        signInDto.setPassword("pa5sword");

        signInDto2 = new SignInDto();
        signInDto2.setEmail("fisrtname2.secondname@gmail.com");
        signInDto2.setPassword("pa5sword");

        userUpdateDto = new UserUpdateDto();
        userUpdateDto.setFirstName("FirstName3");
        userUpdateDto.setLastName("FirstName3");
        userUpdateDto.setPassword("pa5sword");
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void findAll() {
        ResponseDto responseDto = userService.signUp(signupDto3);
        assertThat(userRepository.findAll(), not(IsEmptyCollection.empty()));
        assertThat(responseDto, hasProperty("status", is("success")));
    }

    @Test
    public void signUp() {
        ResponseDto responseDto = userService.signUp(signupDto);
        assertThat(responseDto, hasProperty("status", is("success")));
    }

    @Test
    public void signIn() {
        ResponseDto responseDto = userService.signUp(signupDto2);
        SignInResponseDto sighResponseDto = userService.signIn(signInDto2);
        assertThat(responseDto, hasProperty("status", is("success")));
    }

    @Test
    public void updateUser() {
        SignInResponseDto responseDto = userService.signIn(signInDto);
        authenticationService.authenticate(responseDto.getToken());
        try {
            userService.updateUser(responseDto.getToken(), userUpdateDto);
            fail("Should have thrown an exception");
        } catch (final RuntimeException e) {
            assertTrue(true);
        }
    }

    @AfterEach
    public void clean() {
        mongodExecutable.stop();
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}