package lk.rythmogrp.transactionservice.feign;

import lk.rythmogrp.transactionservice.dto.BaseResponse;
import lk.rythmogrp.transactionservice.dto.EmployeeDTO;
import lk.rythmogrp.transactionservice.feign.config.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "employee-service", value = "employee-service", configuration = {FeignRequestInterceptor.class})
public interface EmployeeFeignClient {

    @PostMapping("/api/v1/employee/employeeIds")
    BaseResponse<List<EmployeeDTO>> findByEmployeeIds(@RequestParam("employeeIds") Set<String> employeeIds);
}
