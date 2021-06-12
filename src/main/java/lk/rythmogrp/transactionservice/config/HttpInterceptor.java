package lk.rythmogrp.transactionservice.config;

import lk.rythmogrp.transactionservice.config.dto.ThreadData;
import lk.rythmogrp.transactionservice.config.dto.UserDTO;
import lk.rythmogrp.transactionservice.feign.AuthFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthFeignClient authFeignClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String business = request.getHeader("x-business");
        String company = request.getHeader("x-company");
        String userId = request.getHeader("x-user");
        String token = request.getHeader("authorization");

        UserDTO userData = authFeignClient.findUserData(token);

        if (userData == null) {
            return false;
        }

        Context.getThreadData().set(new ThreadData(business, company, userId, token,userData));
        log.info("Business " + business);
        return super.preHandle(request, response, handler);
    }
}
