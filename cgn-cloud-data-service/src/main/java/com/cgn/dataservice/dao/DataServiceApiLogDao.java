package com.cgn.dataservice.dao;

import com.cgn.dataservice.entity.DataServiceApiLogEntity;
import com.cgn.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据服务 API 日志 DAO
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Mapper
public interface DataServiceApiLogDao extends BaseDao<DataServiceApiLogEntity> {

}
