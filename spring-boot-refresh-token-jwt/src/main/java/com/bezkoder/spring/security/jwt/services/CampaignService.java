package com.bezkoder.spring.security.jwt.services;

import com.bezkoder.spring.security.jwt.models.Campaign;
import com.bezkoder.spring.security.jwt.payload.response.CampaignResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CampaignService {
    List<CampaignResponse> findAllCampaign();

    Campaign save(Campaign campaign);

    Optional<Campaign> getById(long id);

    List<CampaignResponse> findByNameAndTime(String name, LocalDateTime start, LocalDateTime end);

    List<CampaignResponse> findBanner();
}
