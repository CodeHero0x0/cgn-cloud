package com.cgn.framework.mybatis.interceptor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 数据范围
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Data
@AllArgsConstructor
public class DataScope {
    private String sqlFilter;

}
