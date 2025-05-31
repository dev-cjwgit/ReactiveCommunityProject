package com.cjw.reactivecommunityproject.web.system.function_management.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementListVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementModifyVO;
import com.cjw.reactivecommunityproject.web.system.function_management.service.SystemFunctionManagementService;
import com.cjw.reactivecommunityproject.web.system.function_management.validation.SystemFunctionManagementValidationGroup;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/system/function-management")
public class SystemFunctionManagementController {
    private final SystemFunctionManagementService systemFunctionManagementService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<SystemFunctionManagementListEntity>>> readFunctionManagementList(
            @RequestParam(value = "uid", required = false) Long uid
            , @RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "type", required = false) String type
            , @RequestParam(value = "description", required = false) String description
            , @RequestParam(value = "start-date", required = false) String startDate
            , @RequestParam(value = "end-date", required = false) String endDate
            , @RequestParam("page-number") Integer pageNumber
            , @RequestParam("page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(systemFunctionManagementService.readFunctionManagementList(
                SystemFunctionManagementListVO.builder()
                        .uid(uid)
                        .name(name)
                        .type(type)
                        .description(description)
                        .startDate(DateUtils.convert(startDate, DateUtils.YYYY_MM_DD))
                        .endDate(DateUtils.convert(endDate, DateUtils.YYYY_MM_DD))
                        .build()
                , PaginationRequestVO.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<RestResponseVO<SystemFunctionManagementDetailEntity>> detail(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(systemFunctionManagementService.detail(uid));
    }

    @PostMapping
    public ResponseEntity<RestResponseVO<Void>> create(
            @RequestBody @Validated(SystemFunctionManagementValidationGroup.Create.class) SystemFunctionManagementCreateVO systemFunctionManagementCreateVO
    ) {
        return ResponseEntity.ok(systemFunctionManagementService.create(systemFunctionManagementCreateVO));
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<RestResponseVO<Void>> remove(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(systemFunctionManagementService.remove(uid));
    }

    @PatchMapping
    public ResponseEntity<RestResponseVO<Void>> modify(
            @RequestBody @Validated(SystemFunctionManagementValidationGroup.Modify.class) SystemFunctionManagementModifyVO systemFunctionManagementModifyVO
    ) {
        return ResponseEntity.ok(systemFunctionManagementService.modify(systemFunctionManagementModifyVO));
    }
}
