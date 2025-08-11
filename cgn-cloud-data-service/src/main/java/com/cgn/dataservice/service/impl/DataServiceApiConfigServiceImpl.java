package com.cgn.dataservice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cgn.dataservice.convert.DataServiceApiAuthConvert;
import com.cgn.dataservice.convert.DataServiceApiConfigConvert;
import com.cgn.dataservice.dao.DataServiceApiAuthDao;
import com.cgn.dataservice.dao.DataServiceApiConfigDao;
import com.cgn.dataservice.dao.DataServiceApiGroupDao;
import com.cgn.dataservice.dto.SqlDto;
import com.cgn.dataservice.entity.DataServiceApiAuthEntity;
import com.cgn.dataservice.entity.DataServiceApiConfigEntity;
import com.cgn.dataservice.entity.DataServiceApiGroupEntity;
import com.cgn.dataservice.handler.MappingHandlerMapping;
import com.cgn.dataservice.query.DataServiceApiConfigQuery;
import com.cgn.dataservice.service.DataServiceApiConfigService;
import com.cgn.dataservice.vo.DataServiceApiConfigVO;
import com.cgn.framework.common.constant.Constant;
import com.cgn.framework.common.exception.ServerException;
import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.dbswitch.core.model.JdbcSelectResult;

import com.cgn.framework.mybatis.service.impl.BaseServiceImpl;
import com.cgn.framework.security.user.SecurityUser;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * 数据服务 API 配置服务 impl
 * <p>
 * 2025/08/07
 *
 * @author zyan
 */
@Service
public class DataServiceApiConfigServiceImpl extends BaseServiceImpl<DataServiceApiConfigDao, DataServiceApiConfigEntity> implements DataServiceApiConfigService {
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private DataServiceApiExecuteServiceImpl apiExecuteService;
    @Resource
    private MappingHandlerMapping mappingHandlerMapping;
    @Resource
    private DataServiceApiAuthDao apiAuthDao;
    @Resource
    private DataServiceApiGroupDao dataServiceApiGroupDao;

    @Value("${cgn.gateWay.ipPort}")
    private String gateWayIpPort;


    @Override
    public PageResult<DataServiceApiConfigVO> page(DataServiceApiConfigQuery query) {
        IPage<DataServiceApiConfigEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(DataServiceApiConfigConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<DataServiceApiConfigEntity> getWrapper(DataServiceApiConfigQuery query) {
        LambdaQueryWrapper<DataServiceApiConfigEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(query.getGroupId() != null, DataServiceApiConfigEntity::getGroupId, query.getGroupId())
                .like(StrUtil.isNotBlank(query.getName()), DataServiceApiConfigEntity::getName, query.getName())
                .like(StrUtil.isNotBlank(query.getPath()), DataServiceApiConfigEntity::getPath, query.getPath())
                .eq(StrUtil.isNotBlank(query.getContentType()), DataServiceApiConfigEntity::getContentType, query.getContentType())
                .eq(query.getStatus() != null, DataServiceApiConfigEntity::getStatus, query.getStatus())
                .eq(query.getPrevilege() != null, DataServiceApiConfigEntity::getPrevilege, query.getPrevilege())
                .eq(query.getOpenTrans() != null, DataServiceApiConfigEntity::getOpenTrans, query.getOpenTrans())
                .orderByDesc(DataServiceApiConfigEntity::getCreateTime).orderByDesc(DataServiceApiConfigEntity::getId);
        return wrapper;
    }

    @Override
    public PageResult<DataServiceApiConfigVO> pageResource(DataServiceApiConfigQuery query) {
        // 查询参数
        Map<String, Object> params = getParams(query);
        // 分页查询
        query.setOrder("dsac.id");
        IPage<DataServiceApiConfigEntity> page = getPage(query);
        params.put(Constant.PAGE, page);
        // 数据列表
        List<DataServiceApiConfigEntity> list = baseMapper.getResourceList(params);
        List<DataServiceApiConfigVO> vos = DataServiceApiConfigConvert.INSTANCE.convertList(list);
        for (DataServiceApiConfigVO dataServiceApiConfigVO : vos) {
            DataServiceApiGroupEntity groupEntity = dataServiceApiGroupDao.selectById(dataServiceApiConfigVO.getGroupId());
            dataServiceApiConfigVO.setGroup(groupEntity != null ? groupEntity.getPath() : null);
        }
        return new PageResult<>(vos, page.getTotal());
    }

    @Override
    public void save(DataServiceApiConfigVO vo) {
        //获取目录
        DataServiceApiGroupEntity groupEntity = dataServiceApiGroupDao.selectById(vo.getGroupId());
        DataServiceApiConfigEntity entity = DataServiceApiConfigConvert.INSTANCE.convert(vo);
        entity.setOrgId(groupEntity.getOrgId());
        entity.setProjectId(getProjectId());
        //判断路径是否重复
        DataServiceApiConfigEntity one = getOneByPath(vo);
        if (one != null) {
            throw new ServerException(String.format("已存在路径为【%s】的 API 【%s】，路径不可重复！", one.getPath(), one.getName()));
        }
        baseMapper.insert(entity);
    }

    @Override
    public void update(DataServiceApiConfigVO vo) {
        DataServiceApiGroupEntity groupEntity = dataServiceApiGroupDao.selectById(vo.getGroupId());
        DataServiceApiConfigEntity entity = DataServiceApiConfigConvert.INSTANCE.convert(vo);
        entity.setOrgId(groupEntity.getOrgId());
        entity.setProjectId(getProjectId());
        DataServiceApiConfigEntity dbEntity = getById(vo.getId());
        DataServiceApiConfigEntity one = getOneByPath(vo);
        if (one != null && !dbEntity.getPath().equals(one.getPath())) {
            throw new ServerException(String.format("已存在路径为【%s】的 API 【%s】，路径不可重复！", one.getPath(), one.getName()));
        }
        if (entity.getStatus() == 0) {
            entity.setReleaseTime(null);
            entity.setReleaseUserId(null);
        }
        updateById(entity);
        //如果服务已上线，先下线，再上线
        if (entity.getStatus() == 1) {
            mappingHandlerMapping.unregisterMapping(dbEntity);
            mappingHandlerMapping.registerMapping(entity);
        }
    }

    private DataServiceApiConfigEntity getOneByPath(DataServiceApiConfigVO vo) {
        LambdaQueryWrapper<DataServiceApiConfigEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DataServiceApiConfigEntity::getPath, vo.getPath()).last(" limit 1");
        return baseMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
        //同步删除授权信息
        for (Long apiId : idList) {
            LambdaQueryWrapper<DataServiceApiAuthEntity> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(DataServiceApiAuthEntity::getApiId, apiId);
            apiAuthDao.delete(wrapper);
        }
    }

    @Override
    public String getIpPort() {

        return gateWayIpPort + "/data-service/api/";

        //List<ServiceInstance> instances = discoveryClient.getInstances(ServerNames.GATEWAY_SERVER_NAME);
        //return instances.get(0).getHost() + ":" + instances.get(0).getPort() + "/data-service/api/";
    }


    @Override
    public JdbcSelectResult sqlExecute(SqlDto sqlDto) {
        return apiExecuteService.sqlExecute(sqlDto);
    }

    @Override
    public void online(Long id) {
        //注册接口
        DataServiceApiConfigEntity apiConfigEntity = getById(id);
        mappingHandlerMapping.registerMapping(apiConfigEntity);
        //更新状态
        apiConfigEntity.setStatus(1);
        apiConfigEntity.setReleaseTime(new Date());
        apiConfigEntity.setReleaseUserId(SecurityUser.getUserId());
        baseMapper.updateById(apiConfigEntity);
    }


    @Override
    public void offline(Long id) {
        DataServiceApiConfigEntity apiConfigEntity = getById(id);
        mappingHandlerMapping.unregisterMapping(apiConfigEntity);
        //更新状态
        apiConfigEntity.setStatus(0);
        apiConfigEntity.setReleaseTime(null);
        apiConfigEntity.setReleaseUserId(null);
        baseMapper.updateById(apiConfigEntity);
    }

    @Override
    public PageResult<DataServiceApiConfigVO> pageAuth(DataServiceApiConfigQuery query) {
        // 查询参数
        Map<String, Object> params = getParams(query);
        // 分页查询
        query.setOrder("dsac.id");
        IPage<DataServiceApiConfigEntity> page = getPage(query);
        params.put(Constant.PAGE, page);
        // 数据列表
        List<DataServiceApiConfigEntity> list = baseMapper.getAuthList(params);
        return new PageResult<>(DataServiceApiConfigConvert.INSTANCE.convertList(list), page.getTotal());
    }

    @Override
    public List<DataServiceApiConfigEntity> listActive() {
        LambdaQueryWrapper<DataServiceApiConfigEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DataServiceApiConfigEntity::getStatus, 1);
        return baseMapper.selectList(wrapper);
    }


    @Override
    public void exportDocs(List<Long> ids, HttpServletResponse response) {
        List<String> ipAndPort = StrUtil.split(gateWayIpPort, StrUtil.C_COLON);
        StringBuilder docs = new StringBuilder("## 接口文档\n---\n");
        List<DataServiceApiConfigEntity> apiConfigEntities = baseMapper.selectBatchIds(ids);
        for (DataServiceApiConfigEntity api : apiConfigEntities) {
            docs.append("### ").append(api.getName())
                    .append("\n- IP地址：").append(ipAndPort.get(0))
                    .append("\n- 端口：").append(ipAndPort.get(1))
                    .append("\n- 接口地址：/data-service/api/").append(api.getPath())
                    .append("\n- 请求方式：").append(api.getType())
                    .append("\n- Content-Type：").append(api.getContentType())
                    .append("\n- 是否需要鉴权：").append(api.getPrevilege() == 1 ? "是" : "否");
            if (api.getPrevilege() == 1) {
                docs.append("\n- 获取鉴权token：").append("/data-service/api/token/generate?appKey=您的appKey&appSecret=您的appToken");
            }
            docs.append("\n- 接口备注：").append(api.getNote())
                    .append("\n- 请求参数示例：").append("\n```json\n").append(StrUtil.isNotBlank(api.getJsonParam()) ? api.getJsonParam() : "{}").append("\n```\n")
                    .append("\n- 响应结果示例：").append("\n```json\n").append(StrUtil.isNotBlank(api.getResponseResult()) ? api.getResponseResult() : "{\"code\":0,\"msg\":\"success\",\"data\":[]}").append("\n```\n")
                    .append("\n---\n");
        }
        response.setContentType("application/x-msdownload;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=API DOCS.md");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            os.write(docs.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String ipPort() {
        return gateWayIpPort;
//        return IpUtils.getHostIp() + ":8082";
        //List<ServiceInstance> instances = discoveryClient.getInstances(ServerNames.GATEWAY_SERVER_NAME);
        //return instances.get(0).getHost() + ":" + instances.get(0).getPort();

    }

    @Override
    public void resetRequested(Long authId) {
        apiAuthDao.resetRequested(authId);
    }

    private Map<String, Object> getParams(DataServiceApiConfigQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("groupId", query.getGroupId());
        params.put("name", query.getName());
        params.put("path", query.getPath());
        params.put("contentType", query.getContentType());
        params.put("status", query.getStatus());
        params.put("sqlDbType", query.getSqlDbType());
        params.put("databaseId", query.getDatabaseId());
        params.put("previlege", query.getPrevilege());
        params.put("openTrans", query.getOpenTrans());
        params.put("resourceId", query.getResourceId());
        return params;
    }

}
