package com.cgn.quartz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定时任务并发枚举
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Getter
@AllArgsConstructor
public enum ScheduleConcurrentEnum {
    /**
     * 禁止
     */
    NO(0),
    /**
     * 允许
     */
    YES(1);

    private final int value;
}
