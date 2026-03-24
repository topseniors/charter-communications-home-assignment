package com.charter.rewards.repository;

import com.charter.rewards.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCustomerId(String customerId);

    @Query("SELECT DISTINCT t.customerId FROM Transaction t")
    List<String> findDistinctCustomerIds();
}
