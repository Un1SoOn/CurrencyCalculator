package ru.mikhalev.projects.CurrencyCalculator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mikhalev.projects.CurrencyCalculator.exception.AmountLessThanZeroException;
import ru.mikhalev.projects.CurrencyCalculator.exception.WrongRequestedCurrencyException;
import ru.mikhalev.projects.CurrencyCalculator.repository.CurrencyRepository;
import ru.mikhalev.projects.CurrencyCalculator.service.CurrencyService;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Ivan Mikhalev
 *
 * Контроллер, принимающий запросы на калькулятор валют
 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/calculate")
    public BigDecimal getAmountByCurrentCurrencies(
            @RequestParam(value = "currentCurrency")
            String currentCurrency,
            @RequestParam("necessaryCurrency")
            String necessaryCurrency,
            @RequestParam(value = "amount")
            BigDecimal amount) {

        checkAmountInRequest(amount);

        return currencyService.getAmount(currentCurrency, necessaryCurrency, amount);
    }

    @GetMapping("/calculate/archive")
    public BigDecimal getAmountByArchiveCurrencies(
            @RequestParam(value = "necessaryDate")
            LocalDate necessaryDate,
            @RequestParam(value = "currentCurrency")
            String currentCurrency,
            @RequestParam("necessaryCurrency")
            String necessaryCurrency,
            @RequestParam(value = "amount")
            BigDecimal amount) throws JsonProcessingException {

        checkAmountInRequest(amount);

        return currencyService.getAmount(necessaryDate, currentCurrency, necessaryCurrency, amount);
    }

    public void checkAmountInRequest(BigDecimal amount) {
        if(amount.doubleValue() < 0) {
            throw new AmountLessThanZeroException();
        }
    }
}
