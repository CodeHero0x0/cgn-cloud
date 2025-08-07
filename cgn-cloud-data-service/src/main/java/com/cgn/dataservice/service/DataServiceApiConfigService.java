package com.cgn.dataservice.service;


import com.cgn.dataservice.dto.SqlDto;
import com.cgn.dataservice.entity.DataServiceApiConfigEntity;
import com.cgn.dataservice.query.DataServiceApiConfigQuery;
import com.cgn.dataservice.vo.DataServiceApiConfigVO;
import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.dbswitch.core.model.JdbcSelectResult;
import com.cgn.framework.mybatis.service.BaseService;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;


/**
 * 数据服务 API 配置服务
 * <p>
 * 2025/08/07
 *
 * @author zyan
 */
public interface DataServiceApiConfigService extends BaseService<DataServiceApiConfigEntity> {

    /**
     * 分页查询
     *
     * @param query 查询
     * @return {@link PageResult }<{@link DataServiceApiConfigVO }>
     */
    PageResult<DataServiceApiConfigVO> page(DataServiceApiConfigQuery query);

    /**
     * 新增
     *
     * @param vo VO
     */
    void save(DataServiceApiConfigVO vo);

    /**
     * 更新
     *
     * @param vo VO
     */
    void update(DataServiceApiConfigVO vo);

    /**
     * 删除
     *
     * @param idList ID列表
     */
    void delete(List<Long> idList);

    /**
     * 获取 IP 端口
     *
     * @return {@link String }
     */
    String getIpPort();

    /**
     * sql 执行
     *
     * @param sqlDto sql dto
     * @return {@link JdbcSelectResult }
     */
    JdbcSelectResult sqlExecute(SqlDto sqlDto);

    /**
     * 上线
     *
     * @param id id
     */
    void online(Long id);

    /**
     * 下线
     *
     * @param id id
     */
    void offline(Long id);

    PageResult<DataServiceApiConfigVO> pageAuth(DataServiceApiConfigQuery query);

    /**
     * 所有已上线的接口
     *
     * @return {@link List }<{@link DataServiceApiConfigEntity }>
     */
    List<DataServiceApiConfigEntity> listActive();

    /**
     * 导出文档
     *
     * @param ids      身份证
     * @param response 响应
     */
    void exportDocs(List<Long> ids, HttpServletResponse response);

    /**
     * IP端口
     *
     * @return {@link String }
     */
    String ipPort();

    void resetRequested(Long authId);

    PageResult<DataServiceApiConfigVO> pageResource(DataServiceApiConfigQuery query);
}
