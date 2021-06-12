package lk.rythmogrp.transactionservice.feign.impl;

import lk.rythmogrp.transactionservice.config.dto.UserDTO;
import lk.rythmogrp.transactionservice.feign.AuthFeignClient;
import org.springframework.stereotype.Component;

@Component
public class AuthFeignClientImpl implements AuthFeignClient {
    @Override
    public UserDTO findUserData(String token) {
        return null;
    }
}
