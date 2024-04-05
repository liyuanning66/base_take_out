package com.itbase.base.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itbase.base.common.BaseParam;
import com.itbase.base.common.R;
import com.itbase.base.entity.Point;
import com.itbase.base.mapper.PointMapper;
import com.itbase.base.service.PointService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author me
 * @since 2024-04-05
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements PointService {

    @Autowired
    PointMapper pointMapper;


    @Override
    public void delete(String id) {
        pointMapper.deleteById(id);
    }

    @Override
    public List<Map<String, String>> statisticsByWeek() {
        // 获取这一周的开始时间并格式化
        System.out.println(pointMapper.statistics(DateUtil.beginOfWeek(new Date()).toString(BaseParam.timeFormat),
                DateUtil.endOfWeek(new Date()).toString(BaseParam.timeFormat)).toString());
        return pointMapper.statistics(DateUtil.beginOfWeek(new Date()).toString(BaseParam.timeFormat),
                DateUtil.endOfWeek(new Date()).toString(BaseParam.timeFormat));
    }

    @Override
    public List<Map<String, String>> statisticsByMonth() {
        return pointMapper.statistics(DateUtil.endOfMonth(new Date()).toString(BaseParam.timeFormat),
                DateUtil.endOfMonth(new Date()).toString(BaseParam.timeFormat));
    }

    @Override
    public List<Map<String, String>> statisticByDay() {
        return pointMapper.statistics(DateUtil.beginOfDay(new Date()).toString(BaseParam.timeFormat),
                DateUtil.endOfDay(new Date()).toString(BaseParam.timeFormat));
    }

    @Override
    public List<Point> getListByUserId(String userId) {
        LambdaQueryWrapper<Point> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Point::getUserId, userId);
        return pointMapper.selectList(queryWrapper);
    }

//    @Override
//    public R<List<Point>> getListByUserId(String userId) {
//        QueryWrapper<Point> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(Point::getUserId, userId);
//        return null;
//    }


}
