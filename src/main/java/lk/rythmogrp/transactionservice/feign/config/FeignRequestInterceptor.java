package lk.rythmogrp.transactionservice.feign.config;

import feign.RequestInterceptor;
import lk.rythmogrp.transactionservice.config.Context;
import org.springframework.context.annotation.Bean;

public class FeignRequestInterceptor {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
            requestTemplate.header("x-business", Context.getThreadData().get().getBusinessId());
            requestTemplate.header("x-company", Context.getThreadData().get().getCompanyId());
            requestTemplate.header("x-user", Context.getThreadData().get().getUserId());
            requestTemplate.header("authorization", Context.getThreadData().get().getToken());
        };
    }
}
