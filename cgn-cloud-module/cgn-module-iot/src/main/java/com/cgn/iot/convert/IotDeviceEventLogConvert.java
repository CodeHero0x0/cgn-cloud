package com.cgn.iot.convert;

import com.cgn.iot.entity.IotDeviceEventLogEntity;
import com.cgn.iot.vo.IotDeviceEventLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 设备事件日志
 *
 * @author LSF cgn_lsf@163.com
 */
@Mapper
public interface IotDeviceEventLogConvert {
    IotDeviceEventLogConvert INSTANCE = Mappers.getMapper(IotDeviceEventLogConvert.class);

    IotDeviceEventLogEntity convert(IotDeviceEventLogVO vo);

    IotDeviceEventLogVO convert(IotDeviceEventLogEntity entity);

    List<IotDeviceEventLogVO> convertList(List<IotDeviceEventLogEntity> list);

}
