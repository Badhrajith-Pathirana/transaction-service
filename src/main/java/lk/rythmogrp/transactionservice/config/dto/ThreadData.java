package lk.rythmogrp.transactionservice.config.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ThreadData {
    private String businessId;
    private String companyId;
    private String userId;
    private String token;
    private UserDTO userData;
}
