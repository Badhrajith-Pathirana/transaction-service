package lk.rythmogrp.transactionservice.repository;

import lk.rythmogrp.transactionservice.dto.TransactionTypeDTO;
import lk.rythmogrp.transactionservice.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    List<TransactionTypeDTO> findByBusinessCategoryIdAndBusinessId(Long companyId, Long businessId);

    TransactionTypeDTO findByTransactionCodeAndBusinessCategoryIdAndBusinessId(String transactionCode, Long companyId, Long businessId);
}
