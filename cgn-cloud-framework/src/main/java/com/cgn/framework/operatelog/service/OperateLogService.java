package com.cgn.framework.operatelog.service;

import lombok.AllArgsConstructor;
import com.cgn.framework.common.cache.RedisCache;
import com.cgn.framework.common.cache.RedisKeys;
import com.cgn.framework.operatelog.dto.OperateLogDTO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 操作日志服务
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@Service
@AllArgsConstructor
public class OperateLogService {
    private final RedisCache redisCache;

    @Async
    public void saveLog(OperateLogDTO log) {
        String key = RedisKeys.getLogKey();

        // 保存到Redis队列
        redisCache.leftPush(key, log, RedisCache.NOT_EXPIRE);
    }
}
