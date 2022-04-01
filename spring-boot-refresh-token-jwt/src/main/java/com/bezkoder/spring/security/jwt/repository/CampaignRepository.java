package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Campaign;
import com.bezkoder.spring.security.jwt.payload.response.ICampaignResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findAllByDeletedFlagFalse();

    @Query(value = "select id, name, status, used_amount as usedAmount, budget, bid_amount as bidAmount, start_date as startDate, end_date as endDate, title, description, url " +
            "from campaigns " +
            "where deleted_flag = false and name like %:name% " +
            "and ((start_date > :start and end_date < :end ) " +
            "or (start_date < :start and end_date between :start and :end) " +
            "or (start_date between :start and :end and end_date > :end) " +
            "or (start_date < :start and end_date > :end))", nativeQuery = true)
    List<ICampaignResponse> findByNameAndTime(@Param("name") String name, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query(value = "select id, name, status, used_amount as usedAmount, budget, bid_amount as bidAmount, start_date as startDate, end_date as endDate, title, description, url " +
            "from campaigns where deleted_flag = false " +
            "and (budget - used_amount) >= bid_amount " +
            "and (select timezone('Asia/Saigon',now()) as time) between start_date and end_date " +
            "order by bid_amount desc limit 5 ", nativeQuery = true)
    List<ICampaignResponse> findAllBanner();

}
