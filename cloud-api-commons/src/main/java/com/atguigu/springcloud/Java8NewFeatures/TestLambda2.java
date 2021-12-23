package com.atguigu.springcloud.Java8NewFeatures;

import com.atguigu.springcloud.Java8NewFeatures.Service.Swimmable;

/*
* 一、Lambda 表达式的基础语法：Java8中引入新的操作符“->”
*                            箭头操作符将Lambda表达式分为两个部分
* 左侧：Lambda 表达式的参数列表
* 右侧：Lambda 表达式所需执行的功能，即Lambda体
*
* 二、Lambda标准格式
*     (参数列表) - >{}
*     ①、(参数列表):参数列表
*     ②、->：没有实际意义，起到连接的作用
*
 * */
public class TestLambda2 {
    public static void main(String[] args) {

    }

    //练习无返回值的Lambda
    public static void  goSwimming (Swimmable s){
        s.swimming();
    }
}
