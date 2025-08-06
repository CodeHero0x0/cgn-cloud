package com.cgn.dataservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cgn.dataservice.convert.DataServiceApiLogConvert;
import com.cgn.dataservice.dao.DataServiceApiLogDao;
import com.cgn.dataservice.entity.DataServiceApiLogEntity;
import com.cgn.dataservice.query.DataServiceApiLogQuery;
import com.cgn.dataservice.service.DataServiceApiLogService;
import com.cgn.dataservice.vo.DataServiceApiLogVO;
import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.impl.BaseServiceImpl;
import jodd.util.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据服务 API 日志服务 impl
 * <p>
 * 2025/08/06
 *
 * @author zyan
 */
@Service
@AllArgsConstructor
public class DataServiceApiLogServiceImpl extends BaseServiceImpl<DataServiceApiLogDao, DataServiceApiLogEntity> implements DataServiceApiLogService {

	@Override
	public PageResult<DataServiceApiLogVO> page(DataServiceApiLogQuery query) {
		IPage<DataServiceApiLogEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

		return new PageResult<>(DataServiceApiLogConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
	}

	private LambdaQueryWrapper<DataServiceApiLogEntity> getWrapper(DataServiceApiLogQuery query) {
		LambdaQueryWrapper<DataServiceApiLogEntity> wrapper = Wrappers.lambdaQuery();
		wrapper.like(StringUtil.isNotBlank(query.getIp()), DataServiceApiLogEntity::getIp, query.getIp())
				.like(StringUtil.isNotBlank(query.getApiName()), DataServiceApiLogEntity::getApiName, query.getApiName())
				.orderByDesc(DataServiceApiLogEntity::getCreateTime).orderByDesc(DataServiceApiLogEntity::getId);
		return wrapper;
	}

	@Override
	public void save(DataServiceApiLogVO vo) {
		DataServiceApiLogEntity entity = DataServiceApiLogConvert.INSTANCE.convert(vo);
		baseMapper.insert(entity);
	}

	@Override
	public void update(DataServiceApiLogVO vo) {
		DataServiceApiLogEntity entity = DataServiceApiLogConvert.INSTANCE.convert(vo);
		updateById(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(List<Long> idList) {
		removeByIds(idList);
	}

}
