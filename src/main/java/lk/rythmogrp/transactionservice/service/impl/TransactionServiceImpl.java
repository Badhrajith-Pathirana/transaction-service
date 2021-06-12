package lk.rythmogrp.transactionservice.service.impl;

import lk.rythmogrp.transactionservice.config.Context;
import lk.rythmogrp.transactionservice.config.dto.ThreadData;
import lk.rythmogrp.transactionservice.dto.BaseResponse;
import lk.rythmogrp.transactionservice.dto.EmployeeDTO;
import lk.rythmogrp.transactionservice.dto.TransactionDTO;
import lk.rythmogrp.transactionservice.entity.BaseEntity;
import lk.rythmogrp.transactionservice.entity.Transaction;
import lk.rythmogrp.transactionservice.entity.TransactionType;
import lk.rythmogrp.transactionservice.feign.EmployeeFeignClient;
import lk.rythmogrp.transactionservice.repository.TransactionRepository;
import lk.rythmogrp.transactionservice.repository.TransactionTypeRepository;
import lk.rythmogrp.transactionservice.service.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private EmployeeFeignClient employeeFeignClient;

    @Override
    public TransactionDTO saveTransaction(TransactionDTO transactionDTO) {
        Transaction savedEntity = transactionRepository.save(this.convertEntity(transactionDTO, null));
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(savedEntity);
        BaseResponse<List<EmployeeDTO>> employeeResponse =
                employeeFeignClient.findByEmployeeIds(this.getEmployeeIds(transactions));

        return this.convertDTO(savedEntity, null, employeeResponse.getResponse());
    }

    @Override
    public TransactionDTO updateTransaction(TransactionDTO transactionDTO) {
        Optional<Transaction> transactionOpt = this.transactionRepository.findById(transactionDTO.getTransactionId());
        Transaction savedEntity = this.transactionRepository.save(convertEntity(transactionDTO, transactionOpt.get()));
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(savedEntity);
        BaseResponse<List<EmployeeDTO>> employeeResponse =
                employeeFeignClient.findByEmployeeIds(this.getEmployeeIds(transactions));

        return convertDTO(savedEntity, null, employeeResponse.getResponse());
    }

    @Override
    public List<TransactionDTO> findAll() {
        ThreadData threadData = Context.getThreadData().get();
        List<Transaction> allTransactions
                = this.transactionRepository.findByBusinessCategoryIdAndBusinessId(Long.parseLong(threadData.getCompanyId()),
                Long.parseLong(threadData.getBusinessId()));

        Set<String> employeeIds = this.getEmployeeIds(allTransactions);
        BaseResponse<List<EmployeeDTO>> employeeResponse = this.employeeFeignClient.findByEmployeeIds(employeeIds);

        return allTransactions.parallelStream().map(transaction ->
                this.convertDTO(transaction, null, employeeResponse.getResponse())).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findByTransactionDate(Date date) {
        ThreadData threadData = Context.getThreadData().get();
        List<Transaction> transactions = this.transactionRepository
                .findByTransactionDateAndBusinessCategoryIdAndBusinessId(date,
                        Long.parseLong(threadData.getCompanyId()),
                        Long.parseLong(threadData.getBusinessId()));

        Set<String> employeeIds = this.getEmployeeIds(transactions);
        BaseResponse<List<EmployeeDTO>> employeeResponse = this.employeeFeignClient.findByEmployeeIds(employeeIds);

        return transactions.parallelStream()
                .map(transaction -> this.convertDTO(transaction, null, employeeResponse.getResponse()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findByTransactionDateBetween(Date from, Date to) {
        ThreadData threadData = Context.getThreadData().get();
        List<Transaction> transactions = transactionRepository.findByTransactionDateBetweenAndBusinessCategoryIdAndBusinessId(from,
                to,
                Long.parseLong(threadData.getCompanyId()),
                Long.parseLong(threadData.getBusinessId()));

        Set<String> employeeIds = this.getEmployeeIds(transactions);
        BaseResponse<List<EmployeeDTO>> employeeResponse = this.employeeFeignClient.findByEmployeeIds(employeeIds);
        List<TransactionDTO> transactionDTOList =
                transactions.stream().map(t -> this.convertDTO(t, null, employeeResponse.getResponse())).collect(Collectors.toList());

        return transactionDTOList;
    }

    @Override
    public void deleteTransaction(Long transactionId) {

    }

    private Set<String> getEmployeeIds(List<? extends BaseEntity> data) {
        HashSet<String> ids = new HashSet<>();
        data.forEach(baseEntity -> {
            ids.add(baseEntity.getCreatedBy());
            ids.add(baseEntity.getUpdatedBy());
        });

        return ids;
    }

    private TransactionDTO convertDTO(Transaction transaction, TransactionDTO transactionDTO, List<EmployeeDTO> employeeList) {
        if (transactionDTO == null) {
            transactionDTO = new TransactionDTO();
        }

        BeanUtils.copyProperties(transaction, transactionDTO);
        transactionDTO.setCreatedBy(employeeList.stream().filter(employeeDTO -> employeeDTO.getEmployeeId().equals(transaction.getCreatedBy())).findFirst().orElse(null));
        transactionDTO.setUpdatedBy(employeeList.stream().filter(employeeDTO -> employeeDTO.getEmployeeId().equals(transaction.getUpdatedBy())).findFirst().orElse(null));

        return transactionDTO;
    }

    private Transaction convertEntity(TransactionDTO transactionDTO, Transaction transaction) {
        if (transaction == null) {
            transaction = new Transaction();
        }

        BeanUtils.copyProperties(transactionDTO, transaction);
        Optional<TransactionType> transactionTypeOpt = transactionTypeRepository.findById(transactionDTO.getTransactionType().getId());
        transaction.setTransactionType(transactionTypeOpt.isPresent() ? transactionTypeOpt.get() : null);
        transaction.setCreatedBy(transactionDTO.getCreatedBy().getEmployeeId());
        transaction.setUpdatedBy(transactionDTO.getUpdatedBy().getEmployeeId());

        return transaction;
    }
}
