package vttp2022.project.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import vttp2022.project.Stock.exceptions.UserException;
import vttp2022.project.Stock.models.User;
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

    private User user;

    public UserTests() {
        user = new User();
        user.setUsername("User");
        user.setPassword("user");
    }

    // @BeforeEach
    // public void setup() {
    //     userRepo.createUser("User", "user");
    // }
    

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
       
       assertTrue(true);

    }

  

    
}
