package lk.rythmogrp.transactionservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private Double salary;
    private String salaryType;
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private String createdDate;
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private String updateDate;
}
