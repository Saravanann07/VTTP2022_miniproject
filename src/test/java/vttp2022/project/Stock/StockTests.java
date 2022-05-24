package vttp2022.project.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import vttp2022.project.Stock.exceptions.TransactionException;
import vttp2022.project.Stock.services.TransactionService;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class StockTests {

    @Autowired private JdbcTemplate template;

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
            transSvc.addTransaction(23, cal.getTime(), "V", "Visa Inc", 3, 201.85, 605.55);
        } catch (TransactionException ex) {
            assertTrue(true);
            return;
        }
            fail("Did not throw TransactionException");
    }

    // @Test
    // void insertVisaStockShouldPass() {

    //     Calendar cal = Calendar.getInstance();
    //     cal.set(Calendar.MONTH, Calendar.JANUARY);
    //     cal.set(Calendar.DATE, 1);
    //     cal.set(Calendar.YEAR, 2022);

    //     try {
    //         transSvc.addTransaction(28, cal.getTime(), "V", "Visa Inc", 3, 201.85, 605.55);
    //         assertTrue(false);
            
    //     } catch (TransactionException ex) {
            
    //         return;
    //     }
    //         fail("Did not throw TransactionException");
    // }

    @Test
    public  void addTransactionSuccess(){

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.YEAR, 2022);

        Integer quantity = 3;
        Double stockPrice = 1.00;
        Double totalPrice = 3.00;
        
        


        RequestBuilder req = MockMvcRequestBuilders.post("/addTransaction")
            .accept(MediaType.TEXT_HTML_VALUE)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .sessionAttr("username", "Fred")
            .sessionAttr("password", "fred")
            .param("purchaseDate", cal.getTime().toString())
            .param("symbol", "V")
            .param("companyName", "Visa Inc")
            .param("quantity", quantity.toString())
            .param("stockPrice", stockPrice.toString())
            .param("totalPrice", totalPrice.toString());

           

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
            assertEquals(200,statusCode);
        } catch (Exception ex) {
            fail("cannot retrieve response for unsuccessful addition", ex);
            return;
        }
    }

    @AfterAll

    void deleteTestTransaction() {

        String SQL_DELETE_TRANSACTIONS = "delete from transactions where stock_price = ?";
        template.update(SQL_DELETE_TRANSACTIONS, 1.00);    

    }

   
   
}
