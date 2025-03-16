package com.taskforge.task_forge.DTO;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long amount;
    private String currency;
    private String receipt;
}