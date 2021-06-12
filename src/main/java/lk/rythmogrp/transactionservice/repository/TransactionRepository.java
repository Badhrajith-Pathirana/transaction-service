package lk.rythmogrp.transactionservice.repository;

import lk.rythmogrp.transactionservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByBusinessCategoryIdAndBusinessId(Long businessCategoryId, Long businessId);

    List<Transaction> findByTransactionDateAndBusinessCategoryIdAndBusinessId(Date transactionDate, Long businessCategoryId, Long businessId);

    List<Transaction> findByTransactionDateBetweenAndBusinessCategoryIdAndBusinessId(Date fromDate, Date toDate, Long businessCategoryId, Long businessId);
}
