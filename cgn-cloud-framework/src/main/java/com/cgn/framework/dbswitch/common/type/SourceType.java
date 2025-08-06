package com.cgn.framework.dbswitch.common.type;


/**
 * 源类型
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
public enum SourceType {

	/**
	 * 指定表
	 */
	TABLE(1,"指定表"),
	/**
	 * SQL
	 */
	SQL(2,"SQL");


	private Integer code;
	private String name;

	SourceType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
