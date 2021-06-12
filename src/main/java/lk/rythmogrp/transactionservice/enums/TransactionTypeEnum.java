package lk.rythmogrp.transactionservice.enums;

public enum TransactionTypeEnum {

    INCOME("Income", 1),
    EXPENSE("Expense", 2),
    ASSET("Asset", 3),
    Liability("Liability", 4),
    ;
    private final String label;
    private final int id;

    TransactionTypeEnum(String label, int id) {
        this.label = label;
        this.id = id;
    }
}
