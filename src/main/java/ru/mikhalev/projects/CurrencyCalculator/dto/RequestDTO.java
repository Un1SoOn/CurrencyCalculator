package ru.mikhalev.projects.CurrencyCalculator.dto;

import lombok.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Ivan Mikhalev
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestDTO {
    private LocalDate date;
    private BigDecimal amount;
    private String charCode;
}
