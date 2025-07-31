package com.cgn.system.dao;

import com.cgn.framework.mybatis.dao.BaseDao;
import com.cgn.system.entity.SysLogLoginEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Mapper
public interface SysLogLoginDao extends BaseDao<SysLogLoginEntity> {

}
