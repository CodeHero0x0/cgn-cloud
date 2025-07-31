package com.cgn.security.service;

import lombok.AllArgsConstructor;
import com.cgn.framework.security.mobile.MobileUserDetailsService;
import com.cgn.system.convert.SysUserConvert;
import com.cgn.system.dao.SysUserDao;
import com.cgn.system.entity.SysUserEntity;
import com.cgn.system.service.SysUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 手机验证码登录 MobileUserDetailsService
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Service
@AllArgsConstructor
public class MobileUserDetailsServiceImpl implements MobileUserDetailsService {
    private final SysUserDetailsService sysUserDetailsService;
    private final SysUserDao sysUserDao;

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        SysUserEntity userEntity = sysUserDao.getByMobile(mobile);
        if (userEntity == null) {
            throw new UsernameNotFoundException("手机号或验证码错误");
        }

        return sysUserDetailsService.getUserDetails(SysUserConvert.INSTANCE.convertDetail(userEntity));
    }

}
