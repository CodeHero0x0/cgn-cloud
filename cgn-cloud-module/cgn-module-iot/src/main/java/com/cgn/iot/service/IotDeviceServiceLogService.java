package com.cgn.iot.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.iot.entity.IotDeviceServiceLogEntity;
import com.cgn.iot.enums.DeviceCommandEnum;
import com.cgn.iot.query.IotDeviceServiceLogQuery;
import com.cgn.iot.vo.IotDeviceServiceLogVO;

import java.util.List;

/**
 * 设备服务日志
 *
 * @author LSF cgn_lsf@163.com
 */
public interface IotDeviceServiceLogService extends BaseService<IotDeviceServiceLogEntity> {

    PageResult<IotDeviceServiceLogVO> page(IotDeviceServiceLogQuery query);

    void save(IotDeviceServiceLogVO vo);

    void update(IotDeviceServiceLogVO vo);

    void delete(List<Long> idList);

    /**
     * 创建设备服务日志
     *
     * @param deviceId 设备ID
     * @param tenantId 租户ID
     * @param command  服务类型
     * @param eventUid 事件UID
     * @param payload  事件数据
     * @return 设备事件
     */
    IotDeviceServiceLogEntity createDeviceServiceLog(Long deviceId, Long tenantId, DeviceCommandEnum command,
                                                     String eventUid, Object payload);

    /**
     * 创建设备服务日志并保存
     *
     * @param deviceId 设备ID
     * @param tenantId 租户ID
     * @param command  服务类型
     * @param eventUid 事件UID
     * @param payload  事件数据
     * @return 设备事件
     */
    void createAndSaveDeviceServiceLog(Long deviceId, Long tenantId, DeviceCommandEnum command,
                                       String eventUid, Object payload);
}
