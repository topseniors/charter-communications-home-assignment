package com.charter.rewards.controller;

import com.charter.rewards.dto.CustomerRewards;
import com.charter.rewards.service.RewardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardsController {

    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerRewards>> getAllRewards() {
        return ResponseEntity.ok(rewardsService.getRewardsForAllCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerRewards> getRewardsByCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(rewardsService.getRewardsForCustomer(customerId));
    }
}
