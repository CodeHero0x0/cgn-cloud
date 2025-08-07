package com.cgn.framework.common.utils;


/**
 * bean copy util 回调
 * <p>
 * 2025/08/07
 *
 * @author zyan
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack<S, T> {

    /**
     * 定义默认回调方法
     *
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}
