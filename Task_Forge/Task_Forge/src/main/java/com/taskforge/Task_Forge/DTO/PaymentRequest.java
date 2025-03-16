package com.taskforge.Task_Forge.DTO;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long amount;
    private String currency;
    private String receipt;
}
