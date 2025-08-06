package com.cgn.framework.dbswitch.core.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * JDBC 查询结果
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Data
public class JdbcSelectResult implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private List<JdbcSelectResult> results;
	private Boolean ifQuery;
	private String sql;
	private Long time;
	private Boolean success;
	private String errorMsg;
	private Integer count;
	private List<String> columns;
	private List<Map<String, Object>> rowData;


}
