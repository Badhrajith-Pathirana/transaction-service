package lk.rythmogrp.transactionservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ryt_tra_transaction")
@SequenceGenerator(name = "ryt_tra_transaction_seq", sequenceName = "ryt_tra_transaction_seq", initialValue = 1, allocationSize = 1)
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(generator = "ryt_tra_transaction_seq", strategy = GenerationType.SEQUENCE)
    private Long transactionId;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @MapsId(value = "transactionId")
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;
    private Date transactionDate;
}
