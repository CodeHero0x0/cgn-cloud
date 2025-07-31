package com.cgn.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.cgn.framework.mybatis.entity.BaseEntity;

/**
 * 用户岗位关系
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_user_post")
public class SysUserPostEntity extends BaseEntity {
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	* 岗位ID
	*/
	private Long postId;
}
