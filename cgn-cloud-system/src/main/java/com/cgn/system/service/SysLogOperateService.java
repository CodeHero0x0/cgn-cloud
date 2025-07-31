package com.cgn.system.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.system.entity.SysLogOperateEntity;
import com.cgn.system.query.SysLogOperateQuery;
import com.cgn.system.vo.SysLogOperateVO;

/**
 * 操作日志
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
public interface SysLogOperateService extends BaseService<SysLogOperateEntity> {

    PageResult<SysLogOperateVO> page(SysLogOperateQuery query);
}
