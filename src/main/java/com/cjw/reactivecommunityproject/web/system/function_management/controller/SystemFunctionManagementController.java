package com.cjw.reactivecommunityproject.web.system.function_management.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementListVO;
import com.cjw.reactivecommunityproject.web.system.function_management.service.SystemFunctionManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/system/function-management")
public class SystemFunctionManagementController {
    private final SystemFunctionManagementService systemFunctionManagementService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<SystemFunctionManagementListEntity>>> readFunctionManagementList(
            @RequestParam(value = "uid", required = false) Long uid,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "start-date", required = false) String startDate,
            @RequestParam(value = "end-date", required = false) String endDate,
            @RequestParam("page-number") Integer pageNumber,
            @RequestParam("page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(systemFunctionManagementService.readFunctionManagementList(
                SystemFunctionManagementListVO.builder()
                        .uid(uid)
                        .name(name)
                        .type(type)
                        .description(description)
                        .startDate(DateUtils.convert(startDate, DateUtils.yyyy_MM_dd))
                        .endDate(DateUtils.convert(endDate, DateUtils.yyyy_MM_dd))
                        .build()
                , PaginationRequestVO.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<RestResponseVO<SystemFunctionManagementDetailEntity>> readDetail(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(systemFunctionManagementService.readDetail(uid));
    }
}
