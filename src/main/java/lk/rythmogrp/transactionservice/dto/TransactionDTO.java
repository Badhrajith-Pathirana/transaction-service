package lk.rythmogrp.transactionservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.rythmogrp.transactionservice.entity.TransactionType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long transactionId;
    private String description;
    private TransactionTypeDTO transactionType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transactionDate;
    private EmployeeDTO createdBy;
    private EmployeeDTO updatedBy;
    private Long businessId;
    private Long businessCategoryId;
}
