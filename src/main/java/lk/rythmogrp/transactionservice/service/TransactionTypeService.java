package lk.rythmogrp.transactionservice.service;

import lk.rythmogrp.transactionservice.dto.TransactionTypeDTO;

import java.util.List;

public interface TransactionTypeService {

    List<TransactionTypeDTO> getAllTransactions();

    TransactionTypeDTO findByCode(String code);

    TransactionTypeDTO saveTransactionType(TransactionTypeDTO transactionType);

}
