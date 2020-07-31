package com.xpdz.springcloud.controller;

import com.xpdz.springcloud.entities.CommonResult;
import com.xpdz.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL2 = "http://192.168.0.11:7001";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("消费者收到数据--------"+payment);
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){

        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/get/test")
    public void test(){
        Map<String,Object> map = new HashMap<>();
        map.put("mobile","111");
        map.put("code","2");
        restTemplate.put(PAYMENT_URL2,map);
        //return restTemplate.postForObject(PAYMENT_URL2,map,Map.class);
    }

    public static void main(String[] args) {

    }
}
