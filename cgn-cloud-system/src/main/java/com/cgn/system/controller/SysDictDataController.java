package com.cgn.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.cgn.framework.common.utils.PageResult;
import com.cgn.framework.common.utils.Result;
import com.cgn.framework.operatelog.annotations.OperateLog;
import com.cgn.framework.operatelog.enums.OperateTypeEnum;
import com.cgn.system.convert.SysDictDataConvert;
import com.cgn.system.entity.SysDictDataEntity;
import com.cgn.system.query.SysDictDataQuery;
import com.cgn.system.service.SysDictDataService;
import com.cgn.system.vo.SysDictDataVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典数据
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://cgn.net">cgn</a>
 */
@RestController
@RequestMapping("sys/dict/data")
@Tag(name = "字典数据")
@AllArgsConstructor
public class SysDictDataController {
    private final SysDictDataService sysDictDataService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:dict:page')")
    public Result<PageResult<SysDictDataVO>> page(@ParameterObject @Valid SysDictDataQuery query) {
        PageResult<SysDictDataVO> page = sysDictDataService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:dict:info')")
    public Result<SysDictDataVO> get(@PathVariable("id") Long id) {
        SysDictDataEntity entity = sysDictDataService.getById(id);

        return Result.ok(SysDictDataConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('sys:dict:save')")
    public Result<String> save(@RequestBody @Valid SysDictDataVO vo) {
        sysDictDataService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('sys:dict:update')")
    public Result<String> update(@RequestBody @Valid SysDictDataVO vo) {
        sysDictDataService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        sysDictDataService.delete(idList);

        return Result.ok();
    }

}
