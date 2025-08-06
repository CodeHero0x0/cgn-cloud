package com.cgn.dataservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cgn.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 数据服务 API 身份验证实体
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("data_service_api_auth")
public class DataServiceApiAuthEntity extends BaseEntity {

    /**
     * app的id
     */
    private Long appId;

    /**
     * 分组id
     */
    private Long groupId;

    /**
     * api的id
     */
    private Long apiId;

    /**
     * 调用次数 不限次数为-1
     */
    private Integer requestTimes;

    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private Date startTime;
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private Date endTime;

    /**
     * 已调用次数
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Integer requestedTimes;
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Integer requestedSuccessTimes;
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Integer requestedFailedTimes;

    /**
     * 所属项目id
     */
    private Long projectId;

    /**
     * 真删
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;


}
