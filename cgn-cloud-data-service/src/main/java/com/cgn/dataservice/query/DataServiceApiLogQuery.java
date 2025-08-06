package com.cgn.dataservice.query;

import com.cgn.framework.common.query.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 数据服务 API 日志查询
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "数据服务-api请求日志查询")
public class DataServiceApiLogQuery extends Query {
	private String ip;
	private String apiName;
}
