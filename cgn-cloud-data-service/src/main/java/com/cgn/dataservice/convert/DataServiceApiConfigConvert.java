package com.cgn.dataservice.convert;

import com.cgn.dataservice.entity.DataServiceApiConfigEntity;
import com.cgn.dataservice.vo.DataServiceApiConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 数据服务 API 配置转换
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Mapper
public interface DataServiceApiConfigConvert {
    DataServiceApiConfigConvert INSTANCE = Mappers.getMapper(DataServiceApiConfigConvert.class);
    DataServiceApiConfigEntity convert(DataServiceApiConfigVO vo);

    DataServiceApiConfigVO convert(DataServiceApiConfigEntity entity);

    List<DataServiceApiConfigVO> convertList(List<DataServiceApiConfigEntity> list);

}
