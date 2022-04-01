package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.constan.Constan;
import com.bezkoder.spring.security.jwt.models.Campaign;
import com.bezkoder.spring.security.jwt.payload.request.CampaignRequest;
import com.bezkoder.spring.security.jwt.payload.response.CampaignResponse;
import com.bezkoder.spring.security.jwt.services.CampaignService;
import com.bezkoder.spring.security.jwt.utils.DateTimeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/campaign")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @GetMapping()
    public ResponseEntity<List<CampaignResponse>> listCampaign(@RequestParam("name") String name,
                                                               @RequestParam("start") String start,
                                                               @RequestParam("end") String end) {
        List<CampaignResponse> campaignList;
        if (!name.equals("") || !start.equals("") || !end.equals("")) {
            if (start.equals("")) {
                start = Constan.START_DATE;
            }
            if (end.equals("")) {
                end = Constan.END_DATE;
            }
            campaignList = campaignService.findByNameAndTime(name, DateTimeUtils.convertLocalDateTime(start),
                    DateTimeUtils.convertLocalDateTime(end));
        } else {
            campaignList = campaignService.findAllCampaign();
        }
        return new ResponseEntity<>(campaignList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<List<CampaignResponse>> createCampaign(@Valid @RequestBody CampaignRequest campaignRequest,
                                                                 BindingResult bindingResult) {
        new CampaignRequest().validate(campaignRequest, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (campaignRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Campaign campaign = new Campaign(campaignRequest.getName(), campaignRequest.getUsedAmount(),
                campaignRequest.getBudget(), campaignRequest.getBidAmount(), campaignRequest.getStartDate(),
                campaignRequest.getEndDate(), campaignRequest.getTitle(), campaignRequest.getDescription(),
                campaignRequest.getUrl(),campaignRequest.getStatus());
        campaignService.save(campaign);
        return new ResponseEntity<>(campaignService.findAllCampaign(), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<List<CampaignResponse>> editCampaign(@RequestBody CampaignRequest campaignRequest,
                                                               BindingResult bindingResult) {
        new CampaignRequest().validate(campaignRequest, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Campaign> campaignOptional = campaignService.getById(campaignRequest.getId());
        if (!campaignOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        campaignOptional.get().setName(campaignRequest.getName());
        campaignOptional.get().setStatus(campaignRequest.getStatus());
        campaignOptional.get().setUsedAmount(campaignRequest.getUsedAmount());
        campaignOptional.get().setBudget(campaignRequest.getBudget());
        campaignOptional.get().setBidAmount(campaignRequest.getBidAmount());
        campaignOptional.get().setStartDate(campaignRequest.getStartDate());
        campaignOptional.get().setEndDate(campaignRequest.getEndDate());
        campaignOptional.get().setTitle(campaignRequest.getTitle());
        campaignOptional.get().setDescription(campaignRequest.getDescription());
        campaignOptional.get().setUrl(campaignRequest.getUrl());
        campaignService.save(campaignOptional.get());
        return new ResponseEntity<>(campaignService.findAllCampaign(), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<List<CampaignResponse>> deleteCampaign(@RequestParam("id") long id) {
        Optional<Campaign> campaign = campaignService.getById(id);
        if (campaign.isPresent()) {
            campaign.get().setDeletedFlag(true);
            this.campaignService.save(campaign.get());
            return new ResponseEntity<>(campaignService.findAllCampaign(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/banner")
    public ResponseEntity<List<CampaignResponse>> listBanner() {
        return new ResponseEntity<>(campaignService.findBanner(), HttpStatus.OK);
    }

    @PutMapping("/banner")
    public ResponseEntity<Boolean> clickBanner(@RequestParam("id") long id) {
        Optional<Campaign> campaign = campaignService.getById(id);
        if (campaign.isPresent()) {
            campaign.get().setUsedAmount(campaign.get().getUsedAmount().add(campaign.get().getBidAmount()));
            this.campaignService.save(campaign.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
