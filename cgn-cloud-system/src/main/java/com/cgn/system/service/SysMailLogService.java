package com.cgn.system.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.system.entity.SysMailLogEntity;
import com.cgn.system.query.SysMailLogQuery;
import com.cgn.system.vo.SysMailLogVO;

import java.util.List;

/**
 * 邮件日志
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysMailLogService extends BaseService<SysMailLogEntity> {

    PageResult<SysMailLogVO> page(SysMailLogQuery query);

    void delete(List<Long> idList);
}
