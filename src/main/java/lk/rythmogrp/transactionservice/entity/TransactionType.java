package lk.rythmogrp.transactionservice.entity;

import lk.rythmogrp.transactionservice.enums.TransactionTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "ryt_tra_transaction_type")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"transaction_code", "business_id", "business_category_id"})})
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "ryt_tra_transaction_type_seq", sequenceName = "ryt_tra_transaction_type_seq", initialValue = 1, allocationSize = 1)
public class TransactionType extends BaseEntity {

    @Id
    @GeneratedValue(generator = "ryt_tra_transaction_type_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String transactionType;
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum type;
    @Column(name = "transaction_code")
    private String transactionCode;
    @OneToMany(orphanRemoval = true, mappedBy = "transactionType")
    private List<Transaction> transactions;

}
