package com.cgn.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.common.utils.Result;
import com.cgn.framework.operatelog.annotations.OperateLog;
import com.cgn.framework.operatelog.enums.OperateTypeEnum;
import com.cgn.system.convert.SysParamsConvert;
import com.cgn.system.entity.SysParamsEntity;
import com.cgn.system.query.SysParamsQuery;
import com.cgn.system.service.SysParamsService;
import com.cgn.system.vo.SysParamsVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 参数管理
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@RestController
@RequestMapping("sys/params")
@Tag(name = "参数管理")
@AllArgsConstructor
public class SysParamsController {
    private final SysParamsService sysParamsService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:params:all')")
    public Result<PageResult<SysParamsVO>> page(@ParameterObject @Valid SysParamsQuery query) {
        PageResult<SysParamsVO> page = sysParamsService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:params:all')")
    public Result<SysParamsVO> get(@PathVariable("id") Long id) {
        SysParamsEntity entity = sysParamsService.getById(id);

        return Result.ok(SysParamsConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('sys:params:all')")
    public Result<String> save(@RequestBody SysParamsVO vo) {
        sysParamsService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:params:all')")
    public Result<String> update(@RequestBody @Valid SysParamsVO vo) {
        sysParamsService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('sys:params:all')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        sysParamsService.delete(idList);

        return Result.ok();
    }
}
