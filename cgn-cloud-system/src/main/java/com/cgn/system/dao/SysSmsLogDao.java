package com.cgn.system.dao;

import com.cgn.framework.mybatis.dao.BaseDao;
import com.cgn.system.entity.SysSmsLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信日志
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysSmsLogDao extends BaseDao<SysSmsLogEntity> {

}
