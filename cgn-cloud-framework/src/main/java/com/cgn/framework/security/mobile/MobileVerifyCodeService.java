package com.cgn.framework.security.mobile;

/**
 * 手机短信登录，验证码效验
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
public interface MobileVerifyCodeService {

    boolean verifyCode(String mobile, String code);
}
