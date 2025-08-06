package com.cgn.dataservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cgn.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据服务 API 日志实体
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("data_service_api_log")
public class DataServiceApiLogEntity extends BaseEntity {

    /**
     * url
     */
    private String url;

    /**
     * 响应状态码
     */
    private Integer status;

    /**
     * 时长
     */
    private Long duration;

    /**
     * IP地址
     */
    private String ip;

    /**
     * app的id
     */
    private Long appId;

    /**
     * api的id
     */
    private Long apiId;

    private String appName;
    private String apiName;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 项目id
     */
    private Long projectId;
    private Long orgId;


}
