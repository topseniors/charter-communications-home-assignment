package com.charter.rewards;

import com.charter.rewards.service.RewardsService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RewardsServiceTest {

    @Test
    void calculatePoints_belowFifty_returnsZero() {
        assertEquals(0, RewardsService.calculatePoints(49.99));
        assertEquals(0, RewardsService.calculatePoints(0));
        assertEquals(0, RewardsService.calculatePoints(50.00));
    }

    @Test
    void calculatePoints_betweenFiftyAndHundred_returnsOnePointPerDollar() {
        assertEquals(25, RewardsService.calculatePoints(75.00));
        assertEquals(50, RewardsService.calculatePoints(100.00));
        assertEquals(1, RewardsService.calculatePoints(51.00));
    }

    @Test
    void calculatePoints_overHundred_returnsBonusPoints() {
        // $120 = 2*20 + 1*50 = 90
        assertEquals(90, RewardsService.calculatePoints(120.00));
        // $150 = 2*50 + 1*50 = 150
        assertEquals(150, RewardsService.calculatePoints(150.00));
        // $200 = 2*100 + 1*50 = 250
        assertEquals(250, RewardsService.calculatePoints(200.00));
    }

    @Test
    void calculatePoints_exactBoundaries() {
        assertEquals(0, RewardsService.calculatePoints(50.00));
        assertEquals(50, RewardsService.calculatePoints(100.00));
        assertEquals(52, RewardsService.calculatePoints(101.00));
    }
}
