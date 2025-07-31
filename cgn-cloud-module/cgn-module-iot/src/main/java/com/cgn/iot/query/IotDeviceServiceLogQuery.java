package com.cgn.iot.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.cgn.framework.common.query.Query;

/**
 * 设备服务日志查询
 *
 * @author LSF cgn_lsf@163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "设备服务日志查询")
public class IotDeviceServiceLogQuery extends Query {
    @Schema(description = "指令")
    private String deviceCommandEnum;

    @Schema(description = "设备id")
    private Long deviceId;
}
