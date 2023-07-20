package ru.mikhalev.projects.CurrencyCalculator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Ivan Mikhalev
 */

@Entity
@Table(name = "currency")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "char_code")
    private String charCode;
    @Column(name = "value")
    private BigDecimal value;
}
