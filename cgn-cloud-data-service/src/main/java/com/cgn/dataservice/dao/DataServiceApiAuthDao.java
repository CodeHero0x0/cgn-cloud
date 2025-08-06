package com.cgn.dataservice.dao;

import com.cgn.dataservice.entity.DataServiceApiAuthEntity;
import com.cgn.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 数据服务-权限关联表
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Mapper
public interface DataServiceApiAuthDao extends BaseDao<DataServiceApiAuthEntity> {

	void increaseRequestedTimes(@Param("id") Long id);

	void increaseRequestedSuccessTimes(Long id);

	void increaseRequestedFailedTimes(Long id);

	void resetRequested(Long authId);
}
