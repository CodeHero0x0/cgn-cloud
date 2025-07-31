package com.cgn.system.convert;

import com.cgn.system.entity.SysUserTokenEntity;
import com.cgn.system.vo.SysUserTokenVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户Token
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysUserTokenConvert {
    SysUserTokenConvert INSTANCE = Mappers.getMapper(SysUserTokenConvert.class);

    SysUserTokenEntity convert(SysUserTokenVO vo);

    SysUserTokenVO convert(SysUserTokenEntity entity);

}
