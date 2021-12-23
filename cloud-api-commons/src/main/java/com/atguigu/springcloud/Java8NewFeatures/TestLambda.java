package com.atguigu.springcloud.Java8NewFeatures;

import com.atguigu.springcloud.Java8NewFeatures.Entity.Employee;
import com.atguigu.springcloud.Java8NewFeatures.Service.MyPredicate;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class TestLambda {

    //原本的匿名内部类
    @Test
    public void test1(){
        //匿名内部类的定义
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        //匿名内部类的使用:将匿名内部类作为参数传递
        TreeSet<Integer> t = new TreeSet<>(com);
    }

    //Lambda改造匿名内部类
    public void test2(){
        Comparator<Integer> com=(x,y)->Integer.compare(x,y);
        TreeSet<Integer> t = new TreeSet<>(com);
    }

    //需求：获取当前公司中年龄大于35的员工信息
    List<Employee> employees = Arrays.asList(
            new Employee("张三",18,22),
            new Employee("李四",12,22),
            new Employee("王五",77,22)
    );









}
