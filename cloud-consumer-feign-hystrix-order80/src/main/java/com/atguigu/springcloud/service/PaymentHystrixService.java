package com.atguigu.springcloud.service;

import com.atguigu.springcloud.service.Impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
//与启动类上的@@EnableFeignClients共同实现负载均衡   //fallback = PaymentFallbackService.class指定处理降级的类
public interface PaymentHystrixService {
    //接口注解对应服务提供者的（CLOUD-PROVIDER-HYSTRIX-PAYMENT）内Controller里对应方法的url，从而实现远程调用
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
