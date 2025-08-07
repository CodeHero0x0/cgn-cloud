package com.cgn.dataservice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cgn.dataservice.convert.DataServiceApiGroupConvert;
import com.cgn.dataservice.dao.DataServiceApiGroupDao;
import com.cgn.dataservice.entity.DataServiceApiConfigEntity;
import com.cgn.dataservice.entity.DataServiceApiGroupEntity;
import com.cgn.dataservice.service.DataServiceApiConfigService;
import com.cgn.dataservice.service.DataServiceApiGroupService;
import com.cgn.dataservice.vo.DataServiceApiGroupVO;
import com.cgn.framework.common.exception.ServerException;
import com.cgn.framework.common.utils.BeanUtil;
import com.cgn.framework.common.utils.BuildTreeUtils;
import com.cgn.framework.common.utils.TreeNodeVo;
import com.cgn.framework.mybatis.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 数据服务 API 组服务 IMPL
 * <p>
 * 2025/08/07
 *
 * @author zyan
 */
@Service
@AllArgsConstructor
public class DataServiceApiGroupServiceImpl extends BaseServiceImpl<DataServiceApiGroupDao, DataServiceApiGroupEntity> implements DataServiceApiGroupService {

    private final DataServiceApiConfigService apiConfigService;

    @Override
    public void save(DataServiceApiGroupVO vo) {
        DataServiceApiGroupEntity entity = DataServiceApiGroupConvert.INSTANCE.convert(vo);
        entity.setPath(recursionPath(entity, null));
        entity.setProjectId(getProjectId());
        baseMapper.insert(entity);
    }

    @Override
    public void update(DataServiceApiGroupVO vo) {
        DataServiceApiGroupEntity entity = DataServiceApiGroupConvert.INSTANCE.convert(vo);
        entity.setPath(recursionPath(entity, null));
        entity.setProjectId(getProjectId());
        updateById(entity);
        LambdaUpdateWrapper<DataServiceApiConfigEntity> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(DataServiceApiConfigEntity::getGroupId, vo.getId());
        wrapper.set(DataServiceApiConfigEntity::getOrgId, vo.getOrgId());
        apiConfigService.update(wrapper);
    }

    private String recursionPath(DataServiceApiGroupEntity groupEntity, String path) {
        if (StrUtil.isBlank(path)) {
            path = groupEntity.getName();
        }
        if (groupEntity.getParentId() != 0) {
            DataServiceApiGroupEntity parent = getById(groupEntity.getParentId());
            path = parent.getName() + "/" + path;
            return recursionPath(parent, path);
        }
        return path;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        //查询有没有子节点
        LambdaQueryWrapper<DataServiceApiGroupEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DataServiceApiGroupEntity::getParentId, id).last(" limit 1");
        DataServiceApiGroupEntity one = baseMapper.selectOne(wrapper);
        if (one != null) {
            throw new ServerException("存在子节点，不允许删除！");
        }
        //查询有没有api与之关联
        LambdaQueryWrapper<DataServiceApiConfigEntity> serviceApiConfigWrapper = new LambdaQueryWrapper<>();
        serviceApiConfigWrapper.eq(DataServiceApiConfigEntity::getGroupId, id).last(" limit 1");
        DataServiceApiConfigEntity apiConfigEntity = apiConfigService.getOne(serviceApiConfigWrapper);
        if (apiConfigEntity != null) {
            throw new ServerException("节点下有 api 与之关联，不允许删除！");
        }
        removeById(id);
    }

    @Override
    public List<TreeNodeVo> listTree() {
        LambdaQueryWrapper<DataServiceApiGroupEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(DataServiceApiGroupEntity::getOrderNo);
        List<DataServiceApiGroupEntity> apiGroupEntities = baseMapper.selectList(wrapper);
        List<TreeNodeVo> treeNodeVos = BeanUtil.copyListProperties(apiGroupEntities, TreeNodeVo::new, (oldItem, newItem) -> {
            newItem.setLabel(oldItem.getName());
            if (newItem.getPath().contains("/")) {
                newItem.setParentPath(newItem.getPath().substring(0, newItem.getPath().lastIndexOf("/")));
            }
        });
        return BuildTreeUtils.buildTree(treeNodeVos);
    }

}
