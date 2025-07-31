package com.cgn.system.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.system.entity.SysDictDataEntity;
import com.cgn.system.query.SysDictDataQuery;
import com.cgn.system.vo.SysDictDataVO;

import java.util.List;

/**
 * 数据字典
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    PageResult<SysDictDataVO> page(SysDictDataQuery query);

    void save(SysDictDataVO vo);

    void update(SysDictDataVO vo);

    void delete(List<Long> idList);

}
