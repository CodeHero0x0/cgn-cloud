package com.cgn.quartz.dao;

import com.cgn.framework.mybatis.dao.BaseDao;
import com.cgn.quartz.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 定时任务
*
* @author 阿沐 babamu@126.com
*/
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {

}
