package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")  //没有配置过 @HystrixCommand(fallbackMethod...) 的就用这个降级方法
public class OrderHystirxController {
    @Resource
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand   //没写fallbackMethod = "paymentTimeOutFallbackMethod"就会走DefaultProperties指定的方法
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
//    })
    // @HystrixProperty->指定[timeoutInMilliseconds]线程的超时时间是1500(1s)
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        int num=10/0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    //设置出现服务熔断后采取的降级方案 @HystrixCommand(fallbackMethod...)
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    //@DefaultProperties指定通用方法
     public String payment_Global_FallbackMethod(){
        return "Global 555 对方系统繁忙或者已经宕机，请稍后重试";
     }
}
