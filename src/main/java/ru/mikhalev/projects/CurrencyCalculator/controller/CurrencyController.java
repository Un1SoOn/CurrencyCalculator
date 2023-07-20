package ru.mikhalev.projects.CurrencyCalculator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mikhalev.projects.CurrencyCalculator.dto.RequestDTO;
import ru.mikhalev.projects.CurrencyCalculator.jsonClasses.Valute;
import ru.mikhalev.projects.CurrencyCalculator.service.CurrencyService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Ivan Mikhalev
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    //здесь будет метод, в котором будут использоваться валюты из бд, которые являются актуальными

    @GetMapping("/all")
    public Map<String, Valute> getAllCurrencies() throws JsonProcessingException {
        return currencyService.getAllArchiveValutes();
    }

    ///это будет использоваться для получения архивных валют по дате
    @GetMapping()
    public void getAmountInRubles(
            @RequestParam(value = "date")
            LocalDate date,
            @RequestParam(value = "amount")
            BigDecimal amount,
            @RequestParam(value = "charCode")
            String charCode) {

        RequestDTO requestDTO = RequestDTO.builder()
                .date(date)
                .amount(amount)
                .charCode(charCode)
                .build();


    }
}
