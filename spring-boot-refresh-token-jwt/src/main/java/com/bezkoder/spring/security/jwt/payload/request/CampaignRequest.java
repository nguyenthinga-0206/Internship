package com.bezkoder.spring.security.jwt.payload.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bezkoder.spring.security.jwt.models.Campaign;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRequest implements Validator {
    private long id;

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

    @FutureOrPresent
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @NotBlank
    @Size(min = 3, max = 40)
    private String title;

    @NotBlank
    private String description;

    private String url;

    private Boolean deletedFlag; 

    @Override
    public boolean supports(Class<?> clazz) {
        return Campaign.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CampaignRequest campaign = (CampaignRequest) target;
        LocalDateTime startDate = campaign.startDate;
        LocalDateTime endDate = campaign.endDate;

        if (startDate.isAfter(endDate)) {
            errors.rejectValue("endDate", "endDate.date");
        }
    }
}
