package ru.mikhalev.projects.CurrencyCalculator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mikhalev.projects.CurrencyCalculator.entity.Currency;
import ru.mikhalev.projects.CurrencyCalculator.jsonClasses.Data;
import ru.mikhalev.projects.CurrencyCalculator.jsonClasses.Valute;
import ru.mikhalev.projects.CurrencyCalculator.repository.CurrencyRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final CurrencyRepository currencyRepository;

    public BigDecimal getAmount(String currentCurrency, String necessaryCurrency, BigDecimal amount) {
        if(currentCurrency.equals("RUB")) {
            BigDecimal necessaryCurrencyFromDatabase = currencyRepository.getCurrencyByCharCode(necessaryCurrency).getValue();
            return amount.divide(necessaryCurrencyFromDatabase, 2, RoundingMode.HALF_UP);
        } else if (necessaryCurrency.equals("RUB")) {
            BigDecimal currentCurrencyFromDatabase = currencyRepository.getCurrencyByCharCode(currentCurrency).getValue();
            return currentCurrencyFromDatabase.multiply(amount);
        }
        BigDecimal currentCurrencyValueFromDatabase = currencyRepository.getCurrencyByCharCode(currentCurrency).getValue();
        BigDecimal necessaryCurrencyValueFromDatabase = currencyRepository.getCurrencyByCharCode(necessaryCurrency).getValue();

        return currentCurrencyValueFromDatabase.multiply(amount).divide(necessaryCurrencyValueFromDatabase, 2, RoundingMode.HALF_UP);
    }

    public Map<String ,Valute> getAllArchiveCurrencies() throws JsonProcessingException {
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
