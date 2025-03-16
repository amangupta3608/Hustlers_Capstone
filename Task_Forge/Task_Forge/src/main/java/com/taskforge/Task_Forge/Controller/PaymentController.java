package com.taskforge.task_forge.Controller;


import com.taskforge.task_forge.DTO.PaymentRequest;
import com.taskforge.task_forge.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public String createOrder(@RequestBody PaymentRequest paymentRequest){
        try{
            return paymentService.createOrder(paymentRequest.getAmount(), paymentRequest.getCurrency(), paymentRequest.getReceipt());
        }catch (Exception e){
            return "Error creating order: " + e.getMessage();
        }
    }
}
