package com.cgn.security.service;

import lombok.AllArgsConstructor;
import com.cgn.api.module.system.SmsApi;
import com.cgn.framework.security.mobile.MobileVerifyCodeService;
import org.springframework.stereotype.Service;

/**
 * 短信验证码效验
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Service
@AllArgsConstructor
public class MobileVerifyCodeServiceImpl implements MobileVerifyCodeService {
    private final SmsApi smsApi;

    @Override
    public boolean verifyCode(String mobile, String code) {
        return smsApi.verifyCode(mobile, code);
    }
}
