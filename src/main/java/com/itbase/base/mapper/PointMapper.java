package com.itbase.base.mapper;

import com.itbase.base.entity.Point;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author me
 * @since 2024-04-05
 */
@Mapper
public interface PointMapper extends BaseMapper<Point> {
    /**
     * 周统计
     */
    @MapKey("name")
    List<Map<String, String>> statistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

}
