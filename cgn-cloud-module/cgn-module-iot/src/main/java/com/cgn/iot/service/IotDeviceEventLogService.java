package com.cgn.iot.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.iot.entity.IotDeviceEventLogEntity;
import com.cgn.iot.enums.DeviceEventTypeEnum;
import com.cgn.iot.query.IotDeviceEventLogQuery;
import com.cgn.iot.vo.IotDeviceEventLogVO;

import java.util.List;

/**
 * 设备事件日志
 *
 * @author LSF cgn_lsf@163.com
 */
public interface IotDeviceEventLogService extends BaseService<IotDeviceEventLogEntity> {

    PageResult<IotDeviceEventLogVO> page(IotDeviceEventLogQuery query);

    void save(IotDeviceEventLogVO vo);

    void update(IotDeviceEventLogVO vo);

    void delete(List<Long> idList);

    /**
     * 创建设备事件
     *
     * @param deviceId  设备ID
     * @param tenantId  租户ID
     * @param eventType 事件类型
     * @param eventUid  事件UID
     * @param payload   事件数据
     * @return 设备事件
     */
    IotDeviceEventLogEntity createDeviceEvent(Long deviceId, Long tenantId, DeviceEventTypeEnum eventType,
                                              String eventUid, Object payload);

    /**
     * 创建设备事件并保存
     *
     * @param deviceId  设备ID
     * @param tenantId  租户ID
     * @param eventType 事件类型
     * @param eventUid  事件UID
     * @param payload   事件数据
     * @return 设备事件
     */
    void createAndSaveDeviceEvent(Long deviceId, Long tenantId, DeviceEventTypeEnum eventType,
                                  String eventUid, Object payload);
}
