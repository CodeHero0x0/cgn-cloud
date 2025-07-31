package com.cgn.iot.dao;

import com.cgn.framework.mybatis.dao.BaseDao;
import com.cgn.iot.entity.IotDeviceEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备表
 *
 * @author LSF cgn_lsf@163.com
 */
@Mapper
public interface IotDeviceDao extends BaseDao<IotDeviceEntity> {

}
