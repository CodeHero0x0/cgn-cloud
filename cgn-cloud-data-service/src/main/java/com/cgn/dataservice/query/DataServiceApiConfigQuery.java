package com.cgn.dataservice.query;

import com.cgn.framework.common.query.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 数据服务 API 配置查询
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "数据服务-api配置查询")
public class DataServiceApiConfigQuery extends Query {
	private Long groupId;
	private Long resourceId;
	private Long appId;
	private String name;
	private String path;
	private String contentType;
	private Integer status;
	private Integer sqlDbType;
	private Long databaseId;
	private Integer previlege;
	private Integer openTrans;
}
