package ru.mikhalev.projects.CurrencyCalculator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mikhalev.projects.CurrencyCalculator.service.CurrencyService;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Ivan Mikhalev
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/calculate")
    public BigDecimal getAmount(
            @RequestParam(value = "currentCurrency")
            String currentCurrency,
            @RequestParam("necessaryCurrency")
            String necessaryCurrency,
            @RequestParam(value = "amount")
            BigDecimal amount) {

        return currencyService.getAmount(currentCurrency, necessaryCurrency, amount);

    }

    @GetMapping("/calculate/archive")
    public BigDecimal getAmount(
            @RequestParam(value = "necessaryDate")
            LocalDate necessaryDate,
            @RequestParam(value = "currentCurrency")
            String currentCurrency,
            @RequestParam("necessaryCurrency")
            String necessaryCurrency,
            @RequestParam(value = "amount")
            BigDecimal amount) throws JsonProcessingException {

        return currencyService.getAmount(necessaryDate, currentCurrency, necessaryCurrency, amount);
    }


    /*@GetMapping("/all")
    public Map<String, Valute> getAllCurrenciesByDate() throws JsonProcessingException {
        return currencyService.getAllArchiveCurrencies();
    }*/
}
