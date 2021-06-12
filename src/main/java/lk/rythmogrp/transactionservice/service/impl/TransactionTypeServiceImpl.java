package lk.rythmogrp.transactionservice.service.impl;

import lk.rythmogrp.transactionservice.config.Context;
import lk.rythmogrp.transactionservice.config.dto.ThreadData;
import lk.rythmogrp.transactionservice.dto.TransactionTypeDTO;
import lk.rythmogrp.transactionservice.entity.TransactionType;
import lk.rythmogrp.transactionservice.repository.TransactionTypeRepository;
import lk.rythmogrp.transactionservice.service.TransactionTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Override
    public List<TransactionTypeDTO> getAllTransactions() {
        ThreadData threadData = Context.getThreadData().get();
        List<TransactionTypeDTO> transactionTypes = transactionTypeRepository
                .findByBusinessCategoryIdAndBusinessId(Long.parseLong(threadData.getBusinessId()), Long.parseLong(threadData.getCompanyId()));

        return transactionTypes;
    }

    @Override
    public TransactionTypeDTO findByCode(String code) {
        ThreadData threadData = Context.getThreadData().get();
        TransactionTypeDTO transaction = transactionTypeRepository.
                findByTransactionCodeAndBusinessCategoryIdAndBusinessId(
                        code,
                        Long.parseLong(threadData.getBusinessId()),
                        Long.parseLong(threadData.getCompanyId()));

        return transaction;
    }

    @Override
    public TransactionTypeDTO saveTransactionType(TransactionTypeDTO transactionType) {
        TransactionType entityTra = new TransactionType();
        BeanUtils.copyProperties(transactionType, entityTra);
        TransactionType returnEnt = transactionTypeRepository.save(entityTra);
        TransactionTypeDTO returnDTO = new TransactionTypeDTO();
        BeanUtils.copyProperties(returnEnt, returnDTO);
        return returnDTO;
    }
}
