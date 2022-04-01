package com.bezkoder.spring.security.jwt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaigns")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 40)
    private String name;

    private Boolean status;

    @DecimalMin("0.00")
    @DecimalMax("99999999999999999.00")
    private BigDecimal usedAmount;

    @DecimalMin("0.00")
    @DecimalMax("99999999999999999.00")
    private BigDecimal budget;

    @DecimalMin("0.00")
    @DecimalMax("99999999999999999.00")
    private BigDecimal bidAmount;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @NotBlank
    @Size(min = 3, max = 40)
    private String title;

    @NotBlank
    private String description;

    private String url;

    private Boolean deletedFlag = Boolean.FALSE;

    public Campaign(String name, BigDecimal usedAmount, BigDecimal budget, BigDecimal bidAmount,
                    LocalDateTime startDate, LocalDateTime endDate, String title, String description, String url, Boolean status) {
        this.name = name;
        this.usedAmount = usedAmount;
        this.budget = budget;
        this.bidAmount = bidAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
        this.url = url;
        this.status= status;
    }
}
