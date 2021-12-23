package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
/*@PathVariable和@RequestParam的区别就在于：
@RequestParam用来获得静态的URL请求参数
@PathVariable用来获得动态的URL请求入参

getForEntity()&postForEntity()可获取更多的信息，比如状态码，请求头

* */
@RestController
@Slf4j
public class OrderController {

    public static final String PaymentSrv_URL="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

   /* 新增
    客户端用浏览器是get请求，但是底层实质发送post调用服务*/
    @GetMapping ("/consumer/payment/create")
    public CommonResult create(Payment payment){
       /* 写操作底层是post请求
       访问远程方法用postForObject，传递的三个参数分别是
       请求的url,传递参数，返回参数*/
        return restTemplate.postForObject(PaymentSrv_URL+"/payment/create",payment,CommonResult.class);
     //   return restTemplate.postForEntity(PaymentSrv_URL+"/payment/create",payment,CommonResult.class).getBody();
    }

    @GetMapping ("/consumer/payment/create2")
    public CommonResult create2(Payment payment){
       /* 写操作底层是post请求
       访问远程方法用postForObject，传递的三个参数分别是
       请求的url,传递参数，返回参数*/
        //   return restTemplate.postForObject(PaymentSrv_URL+"/payment/create",payment,CommonResult.class);
        return restTemplate.postForEntity(PaymentSrv_URL+"/payment/create",payment,CommonResult.class).getBody();
    }


    /*查询
    * */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PaymentSrv_URL+"/payment/get/"+id,CommonResult.class,id);
    }

    @GetMapping("/consumer/payment/getPayment2/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PaymentSrv_URL + "/payment/get/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            log.info(forEntity.getStatusCode()+"\t"+forEntity.getHeaders()+"获取信息");
            return forEntity.getBody();
        }else {
            return new CommonResult(444,"操作失败");
        }
    }
}
