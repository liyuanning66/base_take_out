package com.itbase.base.service;

import com.itbase.base.common.R;
import com.itbase.base.entity.Point;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author me
 * @since 2024-04-05
 */
public interface PointService extends IService<Point> {

    void delete(String id);

    /**
     * 周统计
     */
    List<Map<String, String>> statisticsByWeek();
    /**
     * 月统计
     */
    List<Map<String, String>> statisticsByMonth();

    List<Map<String, String>> statisticByDay();

    /**
     * 个人扣分查询
     */
    List<Point> getListByUserId(String userId);
}
