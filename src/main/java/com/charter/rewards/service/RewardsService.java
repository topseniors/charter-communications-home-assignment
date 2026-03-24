package com.charter.rewards.service;

import com.charter.rewards.dto.CustomerRewards;
import com.charter.rewards.model.Transaction;
import com.charter.rewards.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardsService {

    private final TransactionRepository transactionRepository;

    public RewardsService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public static int calculatePoints(double amount) {
        int points = 0;
        int dollars = (int) Math.floor(amount);

        if (dollars > 100) {
            points += (dollars - 100) * 2;
        }
        if (dollars > 50) {
            points += Math.min(dollars - 50, 50);
        }

        return points;
    }

    public CustomerRewards getRewardsForCustomer(String customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("yyyy-MM");

        Map<String, Integer> monthlyPoints = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getDate().format(monthFormat),
                        LinkedHashMap::new,
                        Collectors.summingInt(t -> calculatePoints(t.getAmount()))
                ));

        int totalPoints = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();

        return new CustomerRewards(customerId, monthlyPoints, totalPoints);
    }

    public List<CustomerRewards> getRewardsForAllCustomers() {
        return transactionRepository.findDistinctCustomerIds().stream()
                .map(this::getRewardsForCustomer)
                .collect(Collectors.toList());
    }
}
