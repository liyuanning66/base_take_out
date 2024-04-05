package com.itbase.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itbase.base.entity.Employee;

import java.util.List;

public interface EmployeeService extends IService<Employee> {
    List<Employee> getEmployeeByBuildingName(String userId);
}
