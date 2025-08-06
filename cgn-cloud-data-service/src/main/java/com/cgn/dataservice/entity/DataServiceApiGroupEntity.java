package com.cgn.dataservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cgn.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据服务-api分组
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@EqualsAndHashCode(callSuper=false)
@Data
@TableName("data_service_api_group")
public class DataServiceApiGroupEntity extends BaseEntity {

	/**
	* 父级id（顶级为0）
	*/
	private Long parentId;

	/**
	* 1-文件夹 2-api目录
	*/
	private Integer type;

	/**
	* 名称
	*/
	private String name;

	/**
	* 描述
	*/
	private String description;

	/**
	* 序号
	*/
	private Integer orderNo;

	/**
	* 路径
	*/
	private String path;

	/**
	* 项目id
	*/
	private Long projectId;
	private Long orgId;







}
