package vttp2022.project.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import vttp2022.project.Stock.exceptions.UserException;
import vttp2022.project.Stock.repositories.UserRepository;
import vttp2022.project.Stock.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userSvc;


    @Test
    public void insertSaravananShouldFail() {
        try {
            userSvc.createUser("Saravanan", "sara");
        } catch (UserException ex) {
            assertTrue(true);
            return;
        }

        fail("Did not throw UserException when username is taken");
    }

    

    @Test 
    public void authenticateSaravananShouldPass() {

        int count = 1;

       Integer authenticate =  userRepo.countUsersByNameAndPassword("Saravanan", "sara");
       
       assertEquals(count, authenticate);
    }

    @Test
    public  void createUserFail(){

        RequestBuilder req = MockMvcRequestBuilders.post("/createUser")
            .accept(MediaType.TEXT_HTML_VALUE)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "Fred")
            .param("password", "fred");
            

        // Call the controller
        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation for unsuccessful login", ex);
            return;
        }

        // Get response
        MockHttpServletResponse resp = result.getResponse();
        try {
            Integer statusCode = resp.getStatus();
            assertEquals(400,statusCode);
        } catch (Exception ex) {
            fail("cannot retrieve response for unsuccessful login", ex);
            return;
        }
    }

    @Test
    public  void createUserSuccess(){

        RequestBuilder req = MockMvcRequestBuilders.post("/createUser")
            .accept(MediaType.TEXT_HTML_VALUE)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "SAR")
            .param("password", "sar");
            

        // Call the controller
        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation for unsuccessful registration", ex);
            return;
        }

        // Get response
        MockHttpServletResponse resp = result.getResponse();
        try {
            Integer statusCode = resp.getStatus();
            assertEquals(200,statusCode);
        } catch (Exception ex) {
            fail("cannot retrieve response for unsuccessful registration", ex);
            return;
        }
    }
        
     
}
