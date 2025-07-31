package com.cgn.quartz.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.quartz.entity.ScheduleJobEntity;
import com.cgn.quartz.query.ScheduleJobQuery;
import com.cgn.quartz.vo.ScheduleJobVO;

import java.util.List;

/**
 * 定时任务
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
public interface ScheduleJobService extends BaseService<ScheduleJobEntity> {

    PageResult<ScheduleJobVO> page(ScheduleJobQuery query);

    void save(ScheduleJobVO vo);

    void update(ScheduleJobVO vo);

    void delete(List<Long> idList);

    void run(ScheduleJobVO vo);

    void changeStatus(ScheduleJobVO vo);
}
