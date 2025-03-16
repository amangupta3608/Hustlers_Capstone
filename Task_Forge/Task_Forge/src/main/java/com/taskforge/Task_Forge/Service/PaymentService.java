package com.taskforge.task_forge.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    public String createOrder(Long amount, String currency, String receipt) throws Exception{
        RazorpayClient razorpay = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("Amount", amount);
        orderRequest.put("Currency" , currency);
        orderRequest.put("Receipt", receipt);
        orderRequest.put("Payment_Capture", 1);

        Order order = razorpay.Orders.create(orderRequest);
        return order.toString();
    }
}
