package com.cgn.system.convert;

import com.cgn.email.config.EmailConfig;
import com.cgn.system.entity.SysMailConfigEntity;
import com.cgn.system.vo.SysMailConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 邮件配置
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysMailConfigConvert {
    SysMailConfigConvert INSTANCE = Mappers.getMapper(SysMailConfigConvert.class);

    SysMailConfigEntity convert(SysMailConfigVO vo);

    SysMailConfigVO convert(SysMailConfigEntity entity);

    List<SysMailConfigVO> convertList(List<SysMailConfigEntity> list);

    EmailConfig convert2(SysMailConfigEntity entity);

    List<EmailConfig> convertList2(List<SysMailConfigEntity> list);

}
