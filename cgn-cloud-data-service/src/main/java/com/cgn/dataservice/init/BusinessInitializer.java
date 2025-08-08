package com.cgn.dataservice.init;

import com.cgn.dataservice.entity.DataServiceApiConfigEntity;
import com.cgn.dataservice.handler.MappingHandlerMapping;
import com.cgn.dataservice.service.DataServiceApiConfigService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 业务初始值设定项
 * <p>
 * 2025/08/08
 *
 * @author zyan
 */
@Slf4j
@Component
public class BusinessInitializer implements ApplicationRunner {

    @Resource
    private DataServiceApiConfigService apiConfigService;

    @Resource
    private MappingHandlerMapping mappingHandlerMapping;

    @Override
    public void run(ApplicationArguments args) {
        log.info("init api service");
        //初始化注册已发布的api
        List<DataServiceApiConfigEntity> apiConfigEntities = apiConfigService.listActive();
        for (DataServiceApiConfigEntity api : apiConfigEntities) {
            mappingHandlerMapping.registerMapping(api);
        }
        log.info("init api service end");
    }
}
