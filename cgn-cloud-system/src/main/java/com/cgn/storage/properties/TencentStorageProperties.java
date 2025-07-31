package com.cgn.storage.properties;

import lombok.Data;

/**
 * 腾讯云存储配置项
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Data
public class TencentStorageProperties {
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucketName;
}
