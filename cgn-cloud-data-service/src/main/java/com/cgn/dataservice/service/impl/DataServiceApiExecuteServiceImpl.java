package com.cgn.dataservice.service.impl;

import com.cgn.dataservice.dao.DataServiceApiConfigDao;
import com.cgn.dataservice.dto.SqlDto;
import com.cgn.dataservice.entity.DataServiceApiConfigEntity;
import com.cgn.dataservice.service.DataServiceApiExecuteService;
import com.cgn.framework.common.utils.JsonUtils;
import com.cgn.framework.dbswitch.common.type.ProductTypeEnum;
import com.cgn.framework.dbswitch.core.model.JdbcSelectResult;
import com.cgn.framework.dbswitch.core.service.IMetaDataByJdbcService;
import com.cgn.framework.dbswitch.core.service.impl.MetaDataByJdbcServiceImpl;
import com.cgn.framework.mybatis.service.impl.BaseServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 数据服务 API 执行服务 IMPL
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Service
public class DataServiceApiExecuteServiceImpl extends BaseServiceImpl<DataServiceApiConfigDao, DataServiceApiConfigEntity> implements DataServiceApiExecuteService {

    @Value("${spring.datasource.dynamic.datasource.master.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.dynamic.datasource.master.username}")
    private String userName;

    @Value("${spring.datasource.dynamic.datasource.master.password}")
    private String password;

    @Override
    public JdbcSelectResult sqlExecute(SqlDto sqlDto) {
        Map<String, Object> sqlParam = JsonUtils.parseObject(sqlDto.getJsonParams(), new TypeReference<>() {
        });
        ProductTypeEnum productTypeEnum = ProductTypeEnum.getByIndex(ProductTypeEnum.MYSQL.getIndex());
        IMetaDataByJdbcService metaDataService = new MetaDataByJdbcServiceImpl(productTypeEnum);
        return metaDataService.queryDataByApiSql(jdbcUrl, userName, password, sqlDto.getStatement(), sqlDto.getOpenTrans(), sqlDto.getSqlSeparator(), sqlParam, sqlDto.getSqlMaxRow());
    }

//    @Override
//    public Long verifyToken(String apiToken) {
//        AppToken appToken = JSONUtil.parseObject((String) redisCache.get(RedisKeys.getAppTokenKey(apiToken)), AppToken.class);
//        if (appToken == null) {
//            throw new ServerException("token 不合法！");
//        }
//        Long expireTime = appToken.getExpireAt();
//        // 单次失效
//        if (expireTime == 0) {
//            redisCache.delete(apiToken);
//            return appToken.getAppId();
//        }
//        // 永久有效
//        else if (expireTime == -1) {
//            return appToken.getAppId();
//        }
//        // 设置了有效的失效时间
//        else {
//            if (expireTime > System.currentTimeMillis()) {
//                return appToken.getAppId();
//            } else {
//                // token已经过期就清除
//                redisCache.delete(apiToken);
//                throw new ServerException("token 已过期!");
//            }
//        }
//    }

}
