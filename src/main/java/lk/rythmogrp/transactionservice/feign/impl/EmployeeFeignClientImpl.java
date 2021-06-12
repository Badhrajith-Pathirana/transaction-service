package lk.rythmogrp.transactionservice.feign.impl;

import lk.rythmogrp.transactionservice.dto.BaseResponse;
import lk.rythmogrp.transactionservice.dto.EmployeeDTO;
import lk.rythmogrp.transactionservice.feign.EmployeeFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class EmployeeFeignClientImpl implements EmployeeFeignClient {
    @Override
    public BaseResponse<List<EmployeeDTO>> findByEmployeeIds(Set<String> employeeIds) {
        return null;
    }
}
