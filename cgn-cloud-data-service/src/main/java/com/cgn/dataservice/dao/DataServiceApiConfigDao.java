package com.cgn.dataservice.dao;

import com.cgn.dataservice.entity.DataServiceApiConfigEntity;
import com.cgn.framework.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据服务-api配置
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Mapper
public interface DataServiceApiConfigDao extends BaseDao<DataServiceApiConfigEntity> {

    List<DataServiceApiConfigEntity> getAuthList(Map<String, Object> params);

    List<DataServiceApiConfigEntity> getResourceList(Map<String, Object> params);

    void increaseRequestedTimes(@Param("id") Long id);

    void increaseRequestedSuccessTimes(Long id);

    void increaseRequestedFailedTimes(Long id);


}
