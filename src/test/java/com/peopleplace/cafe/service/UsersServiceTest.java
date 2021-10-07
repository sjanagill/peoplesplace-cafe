package com.peopleplace.cafe.service;

import com.peopleplace.cafe.PeoplesPlaceDemoApplication;
import com.peopleplace.cafe.enums.Role;
import com.peopleplace.cafe.model.User;
import com.peopleplace.cafe.repository.UserRepository;
import de.flapdoodle.embed.mongo.MongodExecutable;
import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PeoplesPlaceDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersServiceTest {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    User user;
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


    }

    @After
    public void tearDown() {
    }

    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @Test
    public void testCreateUser() throws JSONException {

        HttpEntity<User> entity = new HttpEntity<User>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/user/signup"),
                HttpMethod.POST, entity, String.class);
        assertEquals(user, userRepository.save(user));
    }

    @Test
    public void testRetrieveUsers() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/user/all"),
                HttpMethod.GET, entity, String.class);
        if (null == response.getBody() && "null".equalsIgnoreCase(response.getBody()))
            assertEquals(user, response.getBody());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}