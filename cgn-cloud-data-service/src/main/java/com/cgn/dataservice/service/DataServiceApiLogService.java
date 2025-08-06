package com.cgn.dataservice.service;


import com.cgn.dataservice.entity.DataServiceApiLogEntity;
import com.cgn.dataservice.query.DataServiceApiLogQuery;
import com.cgn.dataservice.vo.DataServiceApiLogVO;
import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;

import java.util.List;

/**
 * 数据服务 API 日志服务
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
public interface DataServiceApiLogService extends BaseService<DataServiceApiLogEntity> {

    PageResult<DataServiceApiLogVO> page(DataServiceApiLogQuery query);

    void save(DataServiceApiLogVO vo);

    void update(DataServiceApiLogVO vo);

    void delete(List<Long> idList);
}
