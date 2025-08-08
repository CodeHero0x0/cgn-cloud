package com.cgn.dataservice.controller;


import com.cgn.dataservice.convert.DataServiceApiGroupConvert;
import com.cgn.dataservice.entity.DataServiceApiGroupEntity;
import com.cgn.dataservice.service.DataServiceApiGroupService;
import com.cgn.dataservice.vo.DataServiceApiGroupVO;
import com.cgn.framework.common.utils.Result;
import com.cgn.framework.common.utils.TreeNodeVo;
import com.cgn.framework.operatelog.annotations.OperateLog;
import com.cgn.framework.operatelog.enums.OperateTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 数据服务 API 组控制器
 * <p>
 * 2025/08/07
 *
 * @author zyan
 */
@RestController
@RequestMapping("/api-group")
@Tag(name="数据服务-api分组")
@AllArgsConstructor
public class DataServiceApiGroupController {
    private final DataServiceApiGroupService dataServiceApiGroupService;


	@GetMapping
	@Operation(summary = "查询分组树")
	public Result<List<TreeNodeVo>> listTree() {
		return Result.ok(dataServiceApiGroupService.listTree());
	}


    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('data-service:api-group:info')")
    public Result<DataServiceApiGroupVO> get(@PathVariable("id") Long id){
        DataServiceApiGroupEntity entity = dataServiceApiGroupService.getById(id);

        return Result.ok(DataServiceApiGroupConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('data-service:api-group:save')")
    public Result<String> save(@RequestBody DataServiceApiGroupVO vo){
        dataServiceApiGroupService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('data-service:api-group:update')")
    public Result<String> update(@RequestBody @Valid DataServiceApiGroupVO vo){
        dataServiceApiGroupService.update(vo);

        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('data-service:api-group:delete')")
    public Result<String> delete(@PathVariable Long id){
        dataServiceApiGroupService.delete(id);

        return Result.ok();
    }
}
