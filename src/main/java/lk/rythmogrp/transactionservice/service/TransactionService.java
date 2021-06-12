package lk.rythmogrp.transactionservice.service;

import lk.rythmogrp.transactionservice.dto.TransactionDTO;

import java.util.Date;
import java.util.List;

public interface TransactionService {
    TransactionDTO saveTransaction(TransactionDTO transactionDTO);

    TransactionDTO updateTransaction(TransactionDTO transactionDTO);

    List<TransactionDTO> findAll();

    List<TransactionDTO> findByTransactionDate(Date date);

    List<TransactionDTO> findByTransactionDateBetween(Date from, Date to);

    void deleteTransaction(Long transactionId);
}
