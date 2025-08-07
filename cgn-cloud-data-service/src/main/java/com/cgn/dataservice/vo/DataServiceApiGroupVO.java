package com.cgn.dataservice.vo;

import com.cgn.framework.common.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据服务 API 组 VO
 * <p>
 * 2025/08/07
 *
 * @author zyan
 */
@Data
@Schema(description = "数据服务-api分组")
public class DataServiceApiGroupVO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Schema(description = "主键id")
	private Long id;

	@Schema(description = "父级id（顶级为0）")
	private Long parentId;

	@Schema(description = "1-文件夹 2-api目录")
	private Integer type;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "描述")
	private String description;

	@Schema(description = "序号")
	private Integer orderNo;

	@Schema(description = "路径")
	private String path;

	@Schema(description = "项目id")
	private Long projectId;

	@Schema(description = "版本号")
	private Integer version;

	@Schema(description = "删除标识  0：正常   1：已删除")
	private Integer deleted;

	@Schema(description = "创建者")
	private Long creator;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	@Schema(description = "更新者")
	private Long updater;

	@Schema(description = "更新时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date updateTime;
	private Long orgId;


}
