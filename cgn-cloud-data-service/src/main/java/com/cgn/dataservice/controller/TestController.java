package com.cgn.dataservice.controller;

import com.cgn.dataservice.entity.DataServiceApiConfigEntity;
import com.cgn.dataservice.handler.MappingHandlerMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api-config")
@Tag(name = "数据服务-api配置")
public class TestController {

    @Resource
    private MappingHandlerMapping mappingHandlerMapping;

    @RequestMapping("/test")
    public String test() {
        DataServiceApiConfigEntity apiConfig = new DataServiceApiConfigEntity();
        apiConfig.setName("test");
        apiConfig.setPath("test");
        apiConfig.setType("sql");
        apiConfig.setSqlText("select * from data_service_api_log where id = #{id}");
        apiConfig.setSqlParam("{\r\n    \"id\": 1\r\n}");
        apiConfig.setSqlSeparator(";\\n");
        apiConfig.setSqlMaxRow(1000);
        apiConfig.setType("GET");
        apiConfig.setId(2L);

        mappingHandlerMapping.registerMapping(apiConfig);

        return "test";
    }
}
