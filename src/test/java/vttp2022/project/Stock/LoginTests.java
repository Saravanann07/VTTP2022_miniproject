package vttp2022.project.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

import vttp2022.project.Stock.models.User;



@SpringBootTest
@AutoConfigureMockMvc
public class LoginTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void showLoginPage() {

        RequestBuilder req = MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_HTML_VALUE);

        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot invoke homepage", ex);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        try {
            Integer statusCode = resp.getStatus();
            assertEquals(200,statusCode);
        } catch (Exception ex) {
            fail("cannot retrieve response for homepage", ex);
            return;
        }
    }

    @Test
    public void loginSucces() {

        MockHttpSession session = new MockHttpSession();
        RequestBuilder req = MockMvcRequestBuilders.post("/authenticate")
            .accept(MediaType.TEXT_HTML_VALUE)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "Fred")
            .param("password", "fred")
            .session(session);

        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation for successful login", ex);
            return;
        }

        // Get response
        MockHttpServletResponse resp = result.getResponse();
        try {
            String homepage = resp.getRedirectedUrl();
            assertEquals(null, homepage);
        } catch (Exception ex) {
            fail("cannot retrieve response for successful login", ex);
            return;
        }
    }

    @Test
    public void unsuccessfulLoginTest(){

        MockHttpSession session = new MockHttpSession();
        RequestBuilder req = MockMvcRequestBuilders.post("/authenticate")
            .accept(MediaType.TEXT_HTML_VALUE)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "testabc")
            .param("password", "testabc")
            .session(session);

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
            assertEquals(401,statusCode);
        } catch (Exception ex) {
            fail("cannot retrieve response for unsuccessful login", ex);
            return;
        }
    }

    @Test
    public void logoutTest(){

        MockHttpSession session = new MockHttpSession();
        RequestBuilder req = MockMvcRequestBuilders.get("/logout")
            .accept(MediaType.TEXT_HTML_VALUE)
            .session(session);

        // Call the controller
        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation for logout", ex);
            return;
        }

        // Get response
        MockHttpServletResponse resp = result.getResponse();
        try {
            String redirectedURL = resp.getRedirectedUrl();
            assertEquals(null,redirectedURL);
        } catch (Exception ex) {
            fail("cannot retrieve response for logout", ex);
            return;
        }
    }

    @Test
    public void getHomePage(){


        // MockHttpSession session = new MockHttpSession();

        

        RequestBuilder req = MockMvcRequestBuilders.get("/homepage")
            .accept(MediaType.TEXT_HTML_VALUE)
            .sessionAttr("username", "Fred")
            .sessionAttr("password", "fred");

        // Call the controller
        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation for homepage", ex);
            return;
        }

        // Get response
        MockHttpServletResponse resp = result.getResponse();
        try {
            String redirectedURL = resp.getRedirectedUrl();
            assertEquals(null,redirectedURL);
        } catch (Exception ex) {
            fail("cannot retrieve response for hompage", ex);
            return;
        }
    }

    
    
}
