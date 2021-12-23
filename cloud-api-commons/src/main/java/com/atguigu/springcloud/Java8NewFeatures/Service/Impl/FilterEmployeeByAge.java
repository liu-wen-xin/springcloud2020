package com.atguigu.springcloud.Java8NewFeatures.Service.Impl;

import com.atguigu.springcloud.Java8NewFeatures.Entity.Employee;
import com.atguigu.springcloud.Java8NewFeatures.Service.MyPredicate;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>=35;
    }
}
