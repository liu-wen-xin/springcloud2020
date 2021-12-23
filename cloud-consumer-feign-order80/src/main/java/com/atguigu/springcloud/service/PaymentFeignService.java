package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")   //指定消费者去找哪个微服务
public interface  PaymentFeignService {
    @GetMapping("/payment/get/{id}")        //*****这里的注解需要和服务端调用的接口的Controller的注解信息一样7001/7002
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    //验证Feign的超时时间而设置的方法
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeOut();
}
