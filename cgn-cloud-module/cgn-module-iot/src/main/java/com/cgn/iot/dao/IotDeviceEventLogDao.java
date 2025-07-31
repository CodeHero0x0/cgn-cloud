package com.cgn.iot.dao;

import com.cgn.framework.mybatis.dao.BaseDao;
import com.cgn.iot.entity.IotDeviceEventLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备事件日志
 *
 * @author LSF cgn_lsf@163.com
 */
@Mapper
public interface IotDeviceEventLogDao extends BaseDao<IotDeviceEventLogEntity> {

}
