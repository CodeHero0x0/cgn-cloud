package com.cgn.system.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.cgn.framework.common.query.Query;

/**
 * 短信日志查询
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "短信日志查询")
public class SysSmsLogQuery extends Query {
    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "平台类型")
    private Integer platform;

}
