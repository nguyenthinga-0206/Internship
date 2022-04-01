package com.bezkoder.spring.security.jwt.payload.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ICampaignResponse {
    Long getId();

    String getName();

    Boolean getStatus();

    BigDecimal getUsedAmount();

    BigDecimal getBudget();

    BigDecimal getBidAmount();

    LocalDateTime getStartDate();

    LocalDateTime getEndDate();

    String getTitle();

    String getDescription();

    String getUrl();
}
