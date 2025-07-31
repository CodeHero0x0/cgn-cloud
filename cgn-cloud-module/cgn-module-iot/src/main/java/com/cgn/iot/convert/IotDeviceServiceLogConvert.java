package com.cgn.iot.convert;

import com.cgn.iot.entity.IotDeviceServiceLogEntity;
import com.cgn.iot.vo.IotDeviceServiceLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 设备服务日志
 *
 * @author LSF cgn_lsf@163.com
 */
@Mapper
public interface IotDeviceServiceLogConvert {
    IotDeviceServiceLogConvert INSTANCE = Mappers.getMapper(IotDeviceServiceLogConvert.class);

    IotDeviceServiceLogEntity convert(IotDeviceServiceLogVO vo);

    IotDeviceServiceLogVO convert(IotDeviceServiceLogEntity entity);

    List<IotDeviceServiceLogVO> convertList(List<IotDeviceServiceLogEntity> list);

}
