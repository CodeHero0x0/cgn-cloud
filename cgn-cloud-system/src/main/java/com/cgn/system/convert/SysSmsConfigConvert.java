package com.cgn.system.convert;

import com.cgn.sms.config.SmsConfig;
import com.cgn.system.entity.SysSmsConfigEntity;
import com.cgn.system.vo.SysSmsConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信配置
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysSmsConfigConvert {
    SysSmsConfigConvert INSTANCE = Mappers.getMapper(SysSmsConfigConvert.class);

    SysSmsConfigEntity convert(SysSmsConfigVO vo);

    SysSmsConfigVO convert(SysSmsConfigEntity entity);

    List<SysSmsConfigVO> convertList(List<SysSmsConfigEntity> list);

    SmsConfig convert2(SysSmsConfigEntity entity);

    List<SmsConfig> convertList2(List<SysSmsConfigEntity> list);

}
