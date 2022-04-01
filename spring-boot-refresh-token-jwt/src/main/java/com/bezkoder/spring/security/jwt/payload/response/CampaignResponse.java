package com.bezkoder.spring.security.jwt.payload.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignResponse {
    private long id;
    private String name;
    private boolean status;
    private BigDecimal usedAmount;
    private BigDecimal budget;
    private BigDecimal bidAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;
    private String description;
    private String url;
}
