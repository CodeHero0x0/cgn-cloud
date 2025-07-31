package com.cgn.iot.convert;

import com.cgn.iot.entity.IotDeviceEntity;
import com.cgn.iot.vo.IotDeviceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 设备表
 *
 * @author LSF cgn_lsf@163.com
 */
@Mapper
public interface IotDeviceConvert {
    IotDeviceConvert INSTANCE = Mappers.getMapper(IotDeviceConvert.class);

    IotDeviceEntity convert(IotDeviceVO vo);

    IotDeviceVO convert(IotDeviceEntity entity);

    List<IotDeviceVO> convertList(List<IotDeviceEntity> list);

}
