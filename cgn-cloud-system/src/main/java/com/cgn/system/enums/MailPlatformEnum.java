package com.cgn.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 邮件平台枚举
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Getter
@AllArgsConstructor
public enum MailPlatformEnum {
    /**
     * 本地
     */
    LOCAL(-1),
    /**
     * 阿里云
     */
    ALIYUN(0);

    private final int value;

}
