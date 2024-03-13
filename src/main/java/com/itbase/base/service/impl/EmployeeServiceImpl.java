package com.itbase.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itbase.base.entity.Employee;
import com.itbase.base.mapper.EmployeeMapper;
import com.itbase.base.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
