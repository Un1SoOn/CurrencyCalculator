package ru.mikhalev.projects.CurrencyCalculator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mikhalev.projects.CurrencyCalculator.jsonClasses.Data;
import ru.mikhalev.projects.CurrencyCalculator.jsonClasses.Valute;

import java.util.Map;


/**
 * @author Ivan Mikhalev
 */

@Service
@RequiredArgsConstructor
public class CurrencyService {
    @Value("${url.to.cb}")
    private String URL;
    private final RestTemplate restTemplate= new RestTemplate();

    public Map<String ,Valute> getAllValutes() throws JsonProcessingException {
        String jsonString =  sendRequestTOCB();
        return mapRequestedData(jsonString).getValute();
    }

    public String sendRequestTOCB() {
        return restTemplate.getForObject(URL, String.class);
    }

    public Data mapRequestedData(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper.readValue(jsonString, Data.class);
    }
}
