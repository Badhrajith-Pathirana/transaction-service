package lk.rythmogrp.transactionservice.dto;

import lk.rythmogrp.transactionservice.enums.TransactionTypeEnum;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTypeDTO {
    private Long id;
    private String transactionType;
    private TransactionTypeEnum type;
    private String transactionCode;
}
