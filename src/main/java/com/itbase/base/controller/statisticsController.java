package com.itbase.base.controller;

import cn.hutool.core.date.DateUtil;
import com.itbase.base.common.R;
import com.itbase.base.entity.Point;
import com.itbase.base.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/statistics")
public class statisticsController {
    @Autowired
    PointService pointService;

    /**
     * 周统计
     */
    @GetMapping("statisticsByWeek")
    public R<List<Map<String, String>>> statisticsByWeek(){
        return R.success(pointService.statisticsByWeek());
    }
    /**
     * 月统计
     */
    @GetMapping("statisticsByMonth")
    public R<List<Map<String, String>>> statisticsByMonth(){
        return R.success(pointService.statisticsByMonth());
    }

    /**
     * 天统计
     * @return
     */
    @GetMapping("statisticsByDay")
    public R<List<Map<String, String>>> statisticsByDay(){
        return R.success(pointService.statisticByDay());
    }
}
