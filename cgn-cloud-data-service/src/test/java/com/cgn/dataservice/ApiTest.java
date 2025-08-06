package com.cgn.dataservice;

import com.cgn.dataservice.dto.SqlDto;
import com.cgn.dataservice.service.DataServiceApiExecuteService;
import com.cgn.framework.dbswitch.core.model.JdbcSelectResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class ApiTest {
    @Resource
    private DataServiceApiExecuteService dataServiceApiExecuteService;

    @Test
    public void test1() {
        SqlDto sqlDto = new SqlDto();
        sqlDto.setSqlDbType(1);
        sqlDto.setStatement("select * from sys_dict_data");
        sqlDto.setOpenTrans(0);
        sqlDto.setSqlMaxRow(1000);
        sqlDto.setSqlSeparator(";\\n");
        sqlDto.setJsonParams("{}");
        JdbcSelectResult jdbcSelectResult = dataServiceApiExecuteService.sqlExecute(sqlDto);
        System.out.println(jdbcSelectResult);
    }
}
