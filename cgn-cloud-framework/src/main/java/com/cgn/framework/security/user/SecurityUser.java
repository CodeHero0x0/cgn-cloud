package com.cgn.framework.security.user;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
public class SecurityUser {

    /**
     * 获取用户信息
     */
    public static UserDetail getUser() {
        UserDetail user;
        try {
            user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }

        return user;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        UserDetail user = getUser();
        if (user == null) {
            return null;
        }

        return user.getId();
    }

}
