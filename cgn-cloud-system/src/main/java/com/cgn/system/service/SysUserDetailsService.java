package com.cgn.system.service;

import com.cgn.framework.security.user.UserDetail;
import org.springframework.security.core.userdetails.UserDetails;

public interface SysUserDetailsService {

    /**
     * 获取 UserDetails 对象，设置用户权限信息
     */
    UserDetails getUserDetails(UserDetail userDetail);
}
