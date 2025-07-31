package com.cgn.system.dao;

import com.cgn.framework.mybatis.dao.BaseDao;
import com.cgn.system.entity.SysDictDataEntity;
import com.cgn.system.vo.SysDictVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 字典数据
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Mapper
public interface SysDictDataDao extends BaseDao<SysDictDataEntity> {

    @Select("${sql}")
    List<SysDictVO.DictData> getListForSql(@Param("sql") String sql);
}
