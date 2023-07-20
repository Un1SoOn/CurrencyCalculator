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
    @Value("${url.to.central.bank.archive}")
    private String archiveURL;
    private final RestTemplate restTemplate= new RestTemplate();

    public Map<String ,Valute> getAllArchiveValutes() throws JsonProcessingException {
        String jsonString =  sendRequestToTheCentralBank(archiveURL);
        return mapReceivedData(jsonString).getValute();
    }

    public String sendRequestToTheCentralBank(String URLToTheCentralBank) {
        return restTemplate.getForObject(URLToTheCentralBank, String.class);
    }


    public Data mapReceivedData(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper.readValue(jsonString, Data.class);
    }
}
