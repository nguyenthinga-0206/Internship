package com.bezkoder.spring.security.jwt.services.impl;

import com.bezkoder.spring.security.jwt.models.Campaign;
import com.bezkoder.spring.security.jwt.payload.response.CampaignResponse;
import com.bezkoder.spring.security.jwt.payload.response.ICampaignResponse;
import com.bezkoder.spring.security.jwt.repository.CampaignRepository;
import com.bezkoder.spring.security.jwt.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CampaignServiceImpl implements CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public List<CampaignResponse> findAllCampaign() {
        return campaignRepository.findAllByDeletedFlagFalse()
                .stream()
                .map(this::convertModelToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Campaign save(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public Optional<Campaign> getById(long id) {
        return campaignRepository.findById(id);
    }

    @Override
    public List<CampaignResponse> findByNameAndTime(String name, LocalDateTime start, LocalDateTime end) {
        return campaignRepository.findByNameAndTime(name, start, end)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CampaignResponse> findBanner() {
        return campaignRepository.findAllBanner()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private CampaignResponse convertModelToResponse(Campaign campaign) {
        CampaignResponse campaignResponse = new CampaignResponse();
        campaignResponse.setId(campaign.getId());
        campaignResponse.setName(campaign.getName());
        campaignResponse.setStatus(campaign.getStatus());
        campaignResponse.setUsedAmount(campaign.getUsedAmount());
        campaignResponse.setBudget(campaign.getBudget());
        campaignResponse.setBidAmount(campaign.getBidAmount());
        campaignResponse.setStartDate(campaign.getStartDate());
        campaignResponse.setEndDate(campaign.getEndDate());
        campaignResponse.setTitle(campaign.getTitle());
        campaignResponse.setDescription(campaign.getDescription());
        campaignResponse.setUrl(campaign.getUrl());

        return campaignResponse;
    }

    private CampaignResponse convertToResponse(ICampaignResponse campaign) {
        CampaignResponse campaignResponse = new CampaignResponse();
        campaignResponse.setId(campaign.getId());
        campaignResponse.setName(campaign.getName());
        campaignResponse.setStatus(campaign.getStatus());
        campaignResponse.setUsedAmount(campaign.getUsedAmount());
        campaignResponse.setBudget(campaign.getBudget());
        campaignResponse.setBidAmount(campaign.getBidAmount());
        campaignResponse.setStartDate(campaign.getStartDate());
        campaignResponse.setEndDate(campaign.getEndDate());
        campaignResponse.setTitle(campaign.getTitle());
        campaignResponse.setDescription(campaign.getDescription());
        campaignResponse.setUrl(campaign.getUrl());

        return campaignResponse;
    }
}
