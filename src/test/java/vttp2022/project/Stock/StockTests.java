package vttp2022.project.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;

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

import vttp2022.project.Stock.exceptions.TransactionException;
import vttp2022.project.Stock.services.TransactionService;


@SpringBootTest
@AutoConfigureMockMvc
public class StockTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TransactionService transSvc;


	@Test
	public void getSaravananVisaTransactions() {


        RequestBuilder req = MockMvcRequestBuilders.get("/company?symbol=V")
        .accept(MediaType.TEXT_HTML_VALUE)
        .sessionAttr("username", "Saravanan")
        .sessionAttr("password", "sara");
        

        // Call the controller
        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation for visa stocks", ex);
            return;
        }

        // Get response
        MockHttpServletResponse resp = result.getResponse();
        try {
            String visa = resp.getContentAsString();
            assertNotNull(visa);
        } catch (Exception ex) {
            fail("cannot retrieve response for visa stocks", ex);
            return;
        }
		
	}

    @Test
    public  void addTransactionFail(){

        // Calendar cal = Calendar.getInstance();
        // cal.set(Calendar.MONTH, Calendar.JANUARY);
        // cal.set(Calendar.DATE, 1);
        // cal.set(Calendar.YEAR, 2022);


        RequestBuilder req = MockMvcRequestBuilders.post("/addTransaction")
            .accept(MediaType.TEXT_HTML_VALUE)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .sessionAttr("username", "Fred")
            .sessionAttr("password", "fred");
           

        // Call the controller
        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation for unsuceesful addition", ex);
            return;
        }

        // Get response
        MockHttpServletResponse resp = result.getResponse();
        try {
            Integer statusCode = resp.getStatus();
            assertEquals(400,statusCode);
        } catch (Exception ex) {
            fail("cannot retrieve response for unsuccessful addition", ex);
            return;
        }
    }

    @Test
    void insertVisaStockShouldFail() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.YEAR, 2022);

        try {
            transSvc.addTransaction(23, cal.getTime(), "V", "Visa Inc", 3, 190.7, 572.1);
        } catch (TransactionException ex) {
            assertTrue(true);
            return;
        }
            fail("Did not throw TransactionException");
    }

    @Test
    void insertVisaStockShouldPass() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.YEAR, 2022);

        try {
            transSvc.addTransaction(23, cal.getTime(), "V", "Visa Inc", 3, 190.7, 572.1);
            assertTrue(true);
        } catch (TransactionException ex) {
            return;
        }
            fail("Did not throw TransactionException");
    }

    // @Test
    // public  void addTransactionSuccess(){

    //     Calendar cal = Calendar.getInstance();
    //     cal.set(Calendar.MONTH, Calendar.JANUARY);
    //     cal.set(Calendar.DATE, 1);
    //     cal.set(Calendar.YEAR, 2022);
        


    //     RequestBuilder req = MockMvcRequestBuilders.post("/addTransaction")
    //         .accept(MediaType.TEXT_HTML_VALUE)
    //         .contentType(MediaType.APPLICATION_FORM_URLENCODED)
    //         .sessionAttr("username", "Fred")
    //         .sessionAttr("password", "fred")
    //         .param("purchaseDate", cal.getTime())
    //         .param("symbol", "V")
    //         .param("companyName", "Visa Inc")
    //         .param("quantity", 3)
    //         .param("stockPrice", 190.7)
    //         .param("totalPrice", 572.1);

           

    //     // Call the controller
    //     MvcResult result = null;
    //     try {
    //         result = mvc.perform(req).andReturn();
    //     } catch (Exception ex) {
    //         fail("cannot perform mvc invocation for unsuceesful addition", ex);
    //         return;
    //     }

    //     // Get response
    //     MockHttpServletResponse resp = result.getResponse();
    //     try {
    //         Integer statusCode = resp.getStatus();
    //         assertEquals(200,statusCode);
    //     } catch (Exception ex) {
    //         fail("cannot retrieve response for unsuccessful addition", ex);
    //         return;
    //     }
    // }

   
   
}
