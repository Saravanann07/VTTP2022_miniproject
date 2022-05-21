package vttp2022.project.Stock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.project.Stock.repositories.TransactionRepository;
import vttp2022.project.Stock.services.TransactionService;

@SpringBootTest
@AutoConfigureMockMvc
public class StockTests {

    @Autowired
    private MockMvc mvc;

	@Autowired
	private TransactionRepository transactionRepo;

	@Autowired
	private TransactionService transactionSvc;

	@Test
	public void getSaravananVisaTransactions() {

        String symbol = "V";

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
	
    
}
