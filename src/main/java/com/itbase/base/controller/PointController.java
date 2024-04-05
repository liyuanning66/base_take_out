package com.itbase.base.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbase.base.common.R;
import com.itbase.base.entity.Employee;
import com.itbase.base.entity.Point;
import com.itbase.base.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author me
 * @since 2024-04-05
 */
@RestController
@Slf4j
@RequestMapping("/point")
public class PointController {

    @Autowired
    PointService pointService;

    @GetMapping("page")
    public R<Page> page(int page, int pageSize, String role, int name){
        //测试了一下三个参数传过来是没有问题的
        log.info("page = {}, pageSize = {}, name = {}", page, pageSize, name);
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Point> queryWrapper = new LambdaQueryWrapper();
        //如果角色等于学生
        if(role.equals("1")){
          //查询宿舍相关的扣分情况
          queryWrapper.eq(Point::getName, name);
        }
        queryWrapper.orderByDesc(Point::getCreateTime);
        pointService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PostMapping("save")
    public R<String> save(@RequestBody Point point){
        pointService.save(point);
        return R.success("添加成功");
    }

    @PostMapping("delete")
    public R<String> delete(String id){
        pointService.delete(id);
        return R.success("删除成功");
    }
}

