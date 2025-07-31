package com.cgn.system.convert;

import com.cgn.system.entity.SysLogOperateEntity;
import com.cgn.system.vo.SysLogOperateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 操作日志
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Mapper
public interface SysLogOperateConvert {
    SysLogOperateConvert INSTANCE = Mappers.getMapper(SysLogOperateConvert.class);

    SysLogOperateEntity convert(SysLogOperateVO vo);

    SysLogOperateVO convert(SysLogOperateEntity entity);

    List<SysLogOperateVO> convertList(List<SysLogOperateEntity> list);

}
