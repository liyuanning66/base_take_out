package com.itbase.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbase.base.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
