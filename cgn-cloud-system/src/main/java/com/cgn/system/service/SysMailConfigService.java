package com.cgn.system.service;

import com.cgn.email.config.EmailConfig;
import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.system.entity.SysMailConfigEntity;
import com.cgn.system.query.SysMailConfigQuery;
import com.cgn.system.vo.SysMailConfigVO;

import java.util.List;

/**
 * 邮件平台
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysMailConfigService extends BaseService<SysMailConfigEntity> {

    PageResult<SysMailConfigVO> page(SysMailConfigQuery query);

    List<SysMailConfigVO> list(Integer platform);

    /**
     * 启用的邮件平台列表
     */
    List<EmailConfig> listByEnable();

    void save(SysMailConfigVO vo);

    void update(SysMailConfigVO vo);

    void delete(List<Long> idList);
}
