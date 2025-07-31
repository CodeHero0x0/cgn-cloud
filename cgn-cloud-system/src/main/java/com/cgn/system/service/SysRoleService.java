package com.cgn.system.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.system.entity.SysRoleEntity;
import com.cgn.system.query.SysRoleQuery;
import com.cgn.system.vo.SysRoleDataScopeVO;
import com.cgn.system.vo.SysRoleVO;

import java.util.List;

/**
 * 角色
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

    PageResult<SysRoleVO> page(SysRoleQuery query);

    List<SysRoleVO> getList(SysRoleQuery query);

    void save(SysRoleVO vo);

    void update(SysRoleVO vo);

    void dataScope(SysRoleDataScopeVO vo);

    void delete(List<Long> idList);

    /**
     * 获取角色名称列表
     *
     * @param idList 角色ID列表
     * @return 角色名称列表
     */
    List<String> getNameList(List<Long> idList);
}
