package com.cgn.system.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.system.entity.SysSmsLogEntity;
import com.cgn.system.query.SysSmsLogQuery;
import com.cgn.system.vo.SysSmsLogVO;

import java.util.List;

/**
 * 短信日志
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
public interface SysSmsLogService extends BaseService<SysSmsLogEntity> {

    PageResult<SysSmsLogVO> page(SysSmsLogQuery query);

    void delete(List<Long> idList);
}
