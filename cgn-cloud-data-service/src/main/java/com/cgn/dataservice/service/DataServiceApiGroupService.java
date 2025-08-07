package com.cgn.dataservice.service;



import com.cgn.dataservice.entity.DataServiceApiGroupEntity;
import com.cgn.dataservice.vo.DataServiceApiGroupVO;
import com.cgn.framework.common.utils.TreeNodeVo;
import com.cgn.framework.mybatis.service.BaseService;

import java.util.List;


/**
 * 数据服务 API 组服务
 * <p>
 * 2025/08/07
 *
 * @author zyan
 */
public interface DataServiceApiGroupService extends BaseService<DataServiceApiGroupEntity> {

    void save(DataServiceApiGroupVO vo);

    void update(DataServiceApiGroupVO vo);

    void delete(Long id);

	List<TreeNodeVo> listTree();
}
