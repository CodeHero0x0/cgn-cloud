package com.cgn.system.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.sms.config.SmsConfig;
import com.cgn.system.entity.SysSmsConfigEntity;
import com.cgn.system.query.SysSmsConfigQuery;
import com.cgn.system.vo.SysSmsConfigVO;

import java.util.List;

/**
 * 短信配置
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
public interface SysSmsConfigService extends BaseService<SysSmsConfigEntity> {

    PageResult<SysSmsConfigVO> page(SysSmsConfigQuery query);

    List<SysSmsConfigVO> list(Integer platform);

    /**
     * 启用的短信平台列表
     */
    List<SmsConfig> listByEnable();

    void save(SysSmsConfigVO vo);

    void update(SysSmsConfigVO vo);

    void delete(List<Long> idList);

}
