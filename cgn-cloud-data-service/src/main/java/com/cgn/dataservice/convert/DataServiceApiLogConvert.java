package com.cgn.dataservice.convert;

import com.cgn.dataservice.entity.DataServiceApiLogEntity;
import com.cgn.dataservice.vo.DataServiceApiLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 数据服务 API 日志转换
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Mapper
public interface DataServiceApiLogConvert {
    DataServiceApiLogConvert INSTANCE = Mappers.getMapper(DataServiceApiLogConvert.class);

    DataServiceApiLogEntity convert(DataServiceApiLogVO vo);

    DataServiceApiLogVO convert(DataServiceApiLogEntity entity);

    List<DataServiceApiLogVO> convertList(List<DataServiceApiLogEntity> list);

}
