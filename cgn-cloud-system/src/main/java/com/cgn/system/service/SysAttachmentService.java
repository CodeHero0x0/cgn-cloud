package com.cgn.system.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.system.entity.SysAttachmentEntity;
import com.cgn.system.query.SysAttachmentQuery;
import com.cgn.system.vo.SysAttachmentVO;

import java.util.List;

/**
 * 附件管理
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
public interface SysAttachmentService extends BaseService<SysAttachmentEntity> {

    PageResult<SysAttachmentVO> page(SysAttachmentQuery query);

    void save(SysAttachmentVO vo);

    void update(SysAttachmentVO vo);

    void delete(List<Long> idList);
}
