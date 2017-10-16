package com.stackroute.restfulhackathon.controller;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
 
import com.stackroute.restfulhackathon.RestfulHackathonApplication;
import com.stackroute.restfulhackathon.domain.Users;
 
 
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestfulHackathonApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
   
    @LocalServerPort
    private int port;
   
    TestRestTemplate restTemplate = new TestRestTemplate();
   
    HttpHeaders headers = new HttpHeaders();
   
    Users user1;
    Users user2;
    Users user3;
    Users user4;
   
    @Before
    public void setUp() throws Exception {
         user1 = new Users("Avinash","avinash.prasad@cgi.com");
         user2 = new Users(null,"avinash.prasad@cgi.com");
         user3 = new Users(null,null);
         user4 = new Users("Avinash","avinash.prasadcgi.com");
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port +"/v1.0/hackathon"+ uri;
    }
   
    @After
    public void tearDown() throws Exception {
    }
   
    @Test
    public void testaddUsers() throws Exception {
        System.out.println("testing addUser with valid users");
        HttpEntity<Users> entity = new HttpEntity<Users>(user1, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/users"),
                HttpMethod.POST, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("User Sucessfully added",actual);
    }
   
    @Test
    public void testaddUsesr2() throws Exception {
        HttpEntity<Users> entity = new HttpEntity<Users>(user2, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/users"),
                HttpMethod.POST, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("Invalid usernmae or emailid entered",actual);
    }
   
    @Test
    public void testGetUserList() throws Exception {
        HttpEntity<Users> entity = new HttpEntity<Users>(user1, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/users"),
                HttpMethod.POST, entity, String.class);
        ResponseEntity<String> response1 = restTemplate.exchange(
                createURLWithPort("/users"),
                HttpMethod.POST, entity, String.class);
        HttpEntity<String> entity2 = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response2 = restTemplate.exchange(
                createURLWithPort("/users"),
                HttpMethod.GET, entity2, String.class);
        assertNotNull(response2);
        String actual = response2.getBody();
        System.out.println(actual);
        assertEquals(true,actual.contains("username\":\"Avinash\",\"emailId\":\"avinash.prasad@cgi.com"));
    }
   
    @Test
    public void testDeleteUser() throws Exception {
        //HttpHeaders temp=headers;
        //temp.add("name","ayush");
        HttpEntity<String> entity = new HttpEntity<String>(null,headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/users?id=1"),
                HttpMethod.DELETE, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("user deleted sucessfully",actual);
    }
   
   
}