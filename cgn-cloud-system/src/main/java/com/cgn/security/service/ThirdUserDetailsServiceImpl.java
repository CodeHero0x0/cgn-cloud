package com.cgn.security.service;

import lombok.AllArgsConstructor;
import com.cgn.framework.security.third.ThirdUserDetailsService;
import com.cgn.system.convert.SysUserConvert;
import com.cgn.system.dao.SysUserDao;
import com.cgn.system.entity.SysUserEntity;
import com.cgn.system.service.SysThirdLoginService;
import com.cgn.system.service.SysUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 第三方登录，ThirdUserDetailsService
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Service
@AllArgsConstructor
public class ThirdUserDetailsServiceImpl implements ThirdUserDetailsService {
    private final SysUserDetailsService sysUserDetailsService;
    private final SysThirdLoginService sysThirdLoginService;
    private final SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByOpenTypeAndOpenId(String openType, String openId) throws UsernameNotFoundException {
        Long userId = sysThirdLoginService.getUserIdByOpenTypeAndOpenId(openType, openId);
        SysUserEntity userEntity = sysUserDao.getById(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException("绑定的系统用户，不存在");
        }

        return sysUserDetailsService.getUserDetails(SysUserConvert.INSTANCE.convertDetail(userEntity));
    }
}
