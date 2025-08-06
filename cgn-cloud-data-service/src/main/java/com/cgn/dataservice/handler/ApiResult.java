package com.cgn.dataservice.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


/**
 * API 结果
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResult {
	private Boolean ifQuery;
	private String sql;
	private Long time;
	private Boolean success;
	private String errorMsg;
	private Integer affectedRows;
	private List<String> columns;
	private List<Map<String, Object>> rowData;
}
