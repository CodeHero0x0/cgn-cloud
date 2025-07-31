package com.cgn.iot.dao;

import com.cgn.framework.mybatis.dao.BaseDao;
import com.cgn.iot.entity.IotDeviceServiceLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备服务日志
 *
 * @author LSF cgn_lsf@163.com
 */
@Mapper
public interface IotDeviceServiceLogDao extends BaseDao<IotDeviceServiceLogEntity> {

}
