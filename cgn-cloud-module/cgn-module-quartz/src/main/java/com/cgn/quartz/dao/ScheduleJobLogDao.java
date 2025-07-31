package com.cgn.quartz.dao;

import com.cgn.framework.mybatis.dao.BaseDao;
import com.cgn.quartz.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 定时任务日志
*
* @author 阿沐 babamu@126.com
*/
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {
	
}
