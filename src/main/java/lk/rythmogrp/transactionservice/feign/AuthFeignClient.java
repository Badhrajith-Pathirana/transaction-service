package lk.rythmogrp.transactionservice.feign;

import lk.rythmogrp.transactionservice.config.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "userauth")
public interface AuthFeignClient {

    @PostMapping("/api/auth/private/user")
    public UserDTO findUserData(@RequestHeader("authorization") String token);
}
