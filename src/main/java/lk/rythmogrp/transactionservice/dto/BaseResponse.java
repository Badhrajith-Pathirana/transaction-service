package lk.rythmogrp.transactionservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<Resp> {
    private Long statusCode;
    private String status;
    @JsonProperty("data")
    private Resp response;
}
