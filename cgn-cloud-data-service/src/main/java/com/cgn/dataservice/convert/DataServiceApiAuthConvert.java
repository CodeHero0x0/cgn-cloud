package com.cgn.dataservice.convert;

import com.cgn.dataservice.entity.DataServiceApiAuthEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 数据服务 API 身份验证转换
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Mapper
public interface DataServiceApiAuthConvert {
    DataServiceApiAuthConvert INSTANCE = Mappers.getMapper(DataServiceApiAuthConvert.class);



}
