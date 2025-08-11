package com.cgn.dataservice.handler;

import com.cgn.dataservice.dao.DataServiceApiConfigDao;
import com.cgn.dataservice.dto.SqlDto;
import com.cgn.dataservice.entity.DataServiceApiAuthEntity;
import com.cgn.dataservice.entity.DataServiceApiConfigEntity;
import com.cgn.dataservice.entity.DataServiceApiLogEntity;
import com.cgn.dataservice.service.DataServiceApiExecuteService;
import com.cgn.dataservice.service.DataServiceApiLogService;
import com.cgn.framework.common.exception.ErrorCode;
import com.cgn.framework.common.exception.ServerException;
import com.cgn.framework.common.utils.IpUtils;
import com.cgn.framework.common.utils.JsonUtils;
import com.cgn.framework.common.utils.Result;
import com.cgn.framework.dbswitch.core.model.JdbcSelectResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 映射请求处理程序
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Slf4j
@Component
public class MappingRequestHandler {
    @Resource
    private DataServiceApiConfigDao apiConfigDao;

    @Resource
    private DataServiceApiExecuteService apiExecuteService;

    @Resource
    private DataServiceApiLogService apiLogService;

    @SneakyThrows
    @ResponseBody
    public Object invoke(HttpServletRequest request,
                         @RequestHeader(required = false) String apiToken,
                         @PathVariable(required = false) Map<String, Object> pathVariables,
                         @RequestParam(required = false) Map<String, Object> requestParams,
                         @RequestBody(required = false) Map<String, Object> requestBodys) {
        DataServiceApiConfigEntity apiConfigEntity = null;
        //日志
        long now = System.currentTimeMillis();
        DataServiceApiLogEntity apiLogEntity = new DataServiceApiLogEntity();
        apiLogEntity.setUrl(request.getRequestURI());
        apiLogEntity.setIp(IpUtils.getIpAddr(request));
        apiLogEntity.setStatus(HttpStatus.OK.value());

        try {
            Map<String, Object> params = new HashMap<>();
            if (!CollectionUtils.isEmpty(pathVariables)) {
                log.info("pathVariables:{}", pathVariables);
                params.putAll(pathVariables);
            }
            if (!CollectionUtils.isEmpty(requestParams)) {
                log.info("requestParams:{}", requestParams);
                params.putAll(requestParams);
            }
            if (!CollectionUtils.isEmpty(requestBodys)) {
                log.info("requestBodys:{}", requestBodys);
                params.putAll(requestBodys);
            }
            DataServiceApiConfigEntity mappingApiInfo = MappingHandlerMapping.getMappingApiInfo(request);
            //获取最新的api信息
            apiConfigEntity = apiConfigDao.selectById(mappingApiInfo.getId());
            Assert.notNull(apiConfigEntity, "api已被删除，调用失败");
            //日志
            apiLogEntity.setProjectId(apiConfigEntity.getProjectId());
            apiLogEntity.setApiId(apiConfigEntity.getId());
            apiLogEntity.setApiName(apiConfigEntity.getName());

            if (!request.getMethod().equalsIgnoreCase(apiConfigEntity.getType())) {
                throw new ServerException(String.format("不支持的请求类型，请使用 【%s】方式请求", apiConfigEntity.getType()));
            }

            JdbcSelectResult jdbcSelectResult = apiExecuteService.sqlExecute(SqlDto.builder()
                    .jsonParams(JsonUtils.toJsonString(params))
                    .openTrans(apiConfigEntity.getOpenTrans())
                    .projectId(apiConfigEntity.getProjectId())
                    .sqlSeparator(apiConfigEntity.getSqlSeparator())
                    .sqlMaxRow(apiConfigEntity.getSqlMaxRow())
                    .statement(apiConfigEntity.getSqlText())
                    .build());
            List<JdbcSelectResult> results = jdbcSelectResult.getResults();
            //有任何一条结果错误，则报错
            for (JdbcSelectResult result : results) {
                if (!result.getSuccess()) {
                    throw new ServerException(result.getErrorMsg());
                }
            }
            JdbcSelectResult lastResult = results.isEmpty() ? null : results.get(results.size() - 1);
            apiConfigDao.increaseRequestedSuccessTimes(apiConfigEntity.getId());
            return Result.ok(lastResult != null ? ApiResult.builder().sql(lastResult.getSql()).ifQuery(lastResult.getIfQuery()).success(lastResult.getSuccess()).errorMsg(lastResult.getErrorMsg())
                    .affectedRows(lastResult.getCount()).time(lastResult.getTime()).columns(lastResult.getColumns()).rowData(lastResult.getRowData()).build() : null);
        } catch (Exception e) {
            apiLogEntity.setStatus(ErrorCode.INTERNAL_SERVER_ERROR.getCode());
            apiLogEntity.setError(e.getMessage());
            if (apiConfigEntity != null) {
                //api失败调用次数++
                apiConfigDao.increaseRequestedFailedTimes(apiConfigEntity.getId());
            }
            throw new ServerException(e.getMessage());
        } finally {
            if (apiConfigEntity != null) {
                //api调用次数++
                apiConfigDao.increaseRequestedTimes(apiConfigEntity.getId());
            }
            apiLogEntity.setDuration(System.currentTimeMillis() - now);
            apiLogEntity.setProjectId(1000L);
            //添加日志
            apiLogService.save(apiLogEntity);
            if (apiConfigEntity != null) {
                //api调用次数++
                apiConfigDao.increaseRequestedTimes(apiConfigEntity.getId());

            }
        }
    }

}
