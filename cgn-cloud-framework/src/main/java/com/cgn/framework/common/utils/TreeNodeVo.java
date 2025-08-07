package com.cgn.framework.common.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * 树节点 vo
 * <p>
 * 2025/08/07
 *
 * @author zyan
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNodeVo {
    private Long id;
    private Long parentId;
    private Integer ifLeaf;

    private Long taskId;
    private Integer taskType;
    private String parentPath;
    private String path;
    private Integer orderNo;
    private String label;
    private Long metamodelId;
    private String name;
    private String icon;
    private String code;
    private Integer builtin;
    private String description;
    private Long projectId;
    private Long creator;
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;
    private List<TreeNodeVo> children;
    private boolean disabled;
    private Boolean leaf;
    /**
     * 自定义属性
     */
    private Object attributes;
    /**
     * 自定义类型
     */
    private Object type;
    private Object value;
    private String fileStorageType;

}
