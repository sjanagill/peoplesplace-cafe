package com.peopleplace.cafe.service;

import com.peopleplace.cafe.PeoplesPlaceDemoApplication;
import de.flapdoodle.embed.mongo.MongodExecutable;
import org.json.JSONException;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PeoplesPlaceDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersServiceTest {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    private MongodExecutable mongodExecutable;
    @LocalServerPort
    private int port;

    @After
    public void tearDown() {
    }

    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @Test
    public void testRetrieveStudentCourse() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/users/all"),
                HttpMethod.GET, entity, String.class);

        String expected = "{id:\"1\",name:\"Sonika\",role=\"admin\"}";
        if (null == response.getBody() && "null".equalsIgnoreCase(response.getBody()))
            JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}