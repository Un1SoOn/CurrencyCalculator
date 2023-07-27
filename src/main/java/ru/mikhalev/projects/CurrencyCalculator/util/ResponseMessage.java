package ru.mikhalev.projects.CurrencyCalculator.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author Ivan Mikhalev
 */

@Data
@ToString
@AllArgsConstructor
public class ResponseMessage {
    private String message;
    private LocalDate timestamp;
}
