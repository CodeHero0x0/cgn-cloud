package com.cgn.dataservice.service;


import com.cgn.dataservice.dto.SqlDto;
import com.cgn.dataservice.entity.DataServiceApiConfigEntity;
import com.cgn.framework.dbswitch.core.model.JdbcSelectResult;
import com.cgn.framework.mybatis.service.BaseService;


/**
 * 数据服务 API 执行服务
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
public interface DataServiceApiExecuteService extends BaseService<DataServiceApiConfigEntity> {

    JdbcSelectResult sqlExecute(SqlDto sqlDto);

//	Long verifyToken(String apiToken);
}
