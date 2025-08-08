package com.cgn.dataservice.controller;

import com.cgn.dataservice.query.DataServiceApiLogQuery;
import com.cgn.dataservice.service.DataServiceApiLogService;
import com.cgn.dataservice.vo.DataServiceApiLogVO;
import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.common.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 数据服务 API 日志控制器
 * <p>
 * 2025/08/08
 *
 * @author zyan
 */
@RestController
@RequestMapping("/log")
@Tag(name = "数据服务-api请求日志")
public class DataServiceApiLogController {

    @Resource
    private DataServiceApiLogService dataServiceApiLogService;

    @GetMapping("/page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('data-service:log:page')")
    public Result<PageResult<DataServiceApiLogVO>> page(@Valid DataServiceApiLogQuery query) {
        PageResult<DataServiceApiLogVO> page = dataServiceApiLogService.page(query);

        return Result.ok(page);
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('data-service:log:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        dataServiceApiLogService.delete(idList);

        return Result.ok();
    }
}
