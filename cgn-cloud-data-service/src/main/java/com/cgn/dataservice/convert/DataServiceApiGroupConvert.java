package com.cgn.dataservice.convert;

import com.cgn.dataservice.entity.DataServiceApiGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 数据服务 API 组转换
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Mapper
public interface DataServiceApiGroupConvert {
    DataServiceApiGroupConvert INSTANCE = Mappers.getMapper(DataServiceApiGroupConvert.class);


}
