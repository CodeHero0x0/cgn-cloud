package com.cgn.iot.communication.service;

import com.cgn.iot.entity.IotDeviceEntity;
import com.cgn.iot.enums.DeviceCommandEnum;
import com.cgn.iot.communication.dto.DeviceCommandResponseDTO;


/**
 * 通信协议具备功能
 *
 * @author LSF cgn_lsf@163.com
 */
public interface BaseCommunication {

    // 异步发送指令,不等待设备响应
    String asyncSendCommand(IotDeviceEntity device, DeviceCommandEnum command, String payload);

    //同步发送指定，等待设备响应
    DeviceCommandResponseDTO syncSendCommand(IotDeviceEntity device, DeviceCommandEnum command, String payload);

    //同步发送指定，等待设备响应，调试实现
    DeviceCommandResponseDTO syncSendCommandDebug(IotDeviceEntity device, DeviceCommandEnum command, String payload);

    //模拟设备属性上报
    void simulateDeviceReportAttributeData(IotDeviceEntity device, String payload);

    //模拟设备服务指令响应数据
    void simulateDeviceCommandResponseAttributeData(IotDeviceEntity device, String payload);


}
