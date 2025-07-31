package com.cgn.quartz.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.quartz.entity.ScheduleJobLogEntity;
import com.cgn.quartz.query.ScheduleJobLogQuery;
import com.cgn.quartz.vo.ScheduleJobLogVO;

/**
 * 定时任务日志
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
public interface ScheduleJobLogService extends BaseService<ScheduleJobLogEntity> {

    PageResult<ScheduleJobLogVO> page(ScheduleJobLogQuery query);

}
