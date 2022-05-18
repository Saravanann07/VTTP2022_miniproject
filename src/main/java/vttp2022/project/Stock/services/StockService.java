package vttp2022.project.Stock.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class StockService {
//      //1
    private static final String URL = "https://finnhub.io/api/v1/quote?";

   //export FINNHUB_API_KEY = "lo"
    @Value("${finnhub.api.key")
    private String finnhubKey;

    private boolean hasKey;

    @PostConstruct
    private void init() {
        hasKey = null != finnhubKey;
    }
    

    public Double getQuote(String symbol){

        String quote = UriComponentsBuilder.fromUriString(URL)
            .queryParam("symbol", symbol)
            .queryParam("token", finnhubKey)
            .toUriString();
        System.out.println(">>>>>>>>" + quote);

//          //3
        RequestEntity<Void> req = RequestEntity
            .get(quote)
            .accept(MediaType.APPLICATION_JSON)
            .build();

//         //4
        RestTemplate template = new RestTemplate();

//          //5 
        ResponseEntity<String> resp = template.exchange(req, String.class);

//         //6
        InputStream is = new ByteArrayInputStream(resp.getBody().getBytes());
        JsonReader reader = Json.createReader(is);
        JsonObject obj = reader.readObject();

        Double stockPrice = obj.getJsonNumber("c").doubleValue();
        System.out.println(">>>>>>>>" + stockPrice);
        return stockPrice;
        
    }
    
}
