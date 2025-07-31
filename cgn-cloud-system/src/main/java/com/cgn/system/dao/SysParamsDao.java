package com.cgn.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cgn.framework.mybatis.dao.BaseDao;
import com.cgn.system.entity.SysParamsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参数管理
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Mapper
public interface SysParamsDao extends BaseDao<SysParamsEntity> {

    default boolean isExist(String paramKey) {
        return this.exists(new QueryWrapper<SysParamsEntity>().eq("param_key", paramKey));
    }

    default SysParamsEntity get(String paramKey) {
        return this.selectOne(new QueryWrapper<SysParamsEntity>().eq("param_key", paramKey));
    }
}
