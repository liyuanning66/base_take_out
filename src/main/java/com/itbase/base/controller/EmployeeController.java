package com.itbase.base.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbase.base.common.R;
import com.itbase.base.entity.Employee;
import com.itbase.base.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author me
 * @since 2024-04-05
 */
@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登入
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login") //request是为了给employee对象放一份进入seeion里面
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        /**
         * 1.送过来的密码进行加密
         * 2.根据用户名查数据库比对
         * 3.根据密码查数据库比对
         * 4.查看员工状态是否是被禁用
         * 5.将成功登入的员工id存入Session并返回成功结果结果
         */
        //加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //查数据库根据username查询数据库得到这个emp对象
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //如果用户不存在
        if(emp == null){
            return R.error("登入失败");
        }

        //密码的比对，如果不一致则返回登入失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("登入失败");
        }
        //账户是否被禁用
        if(emp.getStatus() == 0){
            return R.error("账号已禁用");
        }
        //如果登入成功我们将用户的id放入session中去
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //request删除Session中用户的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息：{}", employee.toString());
        //id=null, username=zdddada, name=张三, password=null, phone=14748456154, sex=1, idNumber=148956465421564444
        // , status=null, createTime=null, updateTime=null, createUser=null, updateUser=null
        //设置初始密码，但是需要加缪
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //设置创建的时间和更新的时间 (添加了mybatisplus自动填充功能)
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        //这里我们需要获取createUser的信息
//        long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateUser(empId);
//        employee.setCreateUser(empId);
        //保存对象
        employeeService.save(employee);
        return R.success("新增员工成功");
    }

    /**
     * 数据查询并且分页
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        //测试了一下三个参数传过来是没有问题的
        log.info("page = {}, pageSize = {}, name = {}", page, pageSize, name);
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getUsername, name);
        //添加一个排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }
}

