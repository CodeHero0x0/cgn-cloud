package com.cgn.system.service;

import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.mybatis.service.BaseService;
import com.cgn.system.entity.SysUserEntity;
import com.cgn.system.query.SysRoleUserQuery;
import com.cgn.system.query.SysUserQuery;
import com.cgn.system.vo.SysUserAvatarVO;
import com.cgn.system.vo.SysUserBaseVO;
import com.cgn.system.vo.SysUserVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户管理
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
public interface SysUserService extends BaseService<SysUserEntity> {

    PageResult<SysUserVO> page(SysUserQuery query);

    void save(SysUserVO vo);

    void update(SysUserVO vo);

    void updateLoginInfo(SysUserBaseVO vo);

    void updateAvatar(SysUserAvatarVO avatar);

    void delete(List<Long> idList);

    /**
     * 获取用户姓名列表
     *
     * @param idList 用户ID列表
     * @return 用户姓名列表
     */
    List<String> getRealNameList(List<Long> idList);

    SysUserVO getByMobile(String mobile);

    /**
     * 修改密码
     *
     * @param id          用户ID
     * @param newPassword 新密码
     */
    void updatePassword(Long id, String newPassword);

    /**
     * 分配角色，用户列表
     */
    PageResult<SysUserVO> roleUserPage(SysRoleUserQuery query);

    /**
     * 批量导入用户
     *
     * @param file     excel文件
     * @param password 密码
     */
    void importByExcel(MultipartFile file, String password);

    /**
     * 导出用户信息表格
     */
    void export();
}
