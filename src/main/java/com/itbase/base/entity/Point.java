package com.itbase.base.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author me
 * @since 2024-04-05
 */
@Data
public class Point implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private String id;

    /**
     * 宿舍名称
     */
    private String name;

    /**
     * 扣分详情
     */
    private String details;

    /**
     * 扣分
     */
    private Float score;

    /**
     * 图片
     */
    private String picture;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;

    @TableField(fill = FieldFill.INSERT) //插入时更新数据的值
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) //更新时更新数据的值(下面两个同理)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


}
