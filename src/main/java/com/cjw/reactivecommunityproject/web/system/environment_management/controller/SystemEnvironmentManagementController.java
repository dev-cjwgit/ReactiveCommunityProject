package com.cjw.reactivecommunityproject.web.system.environment_management.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.request.SystemEnvironmentManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.request.SystemEnvironmentManagementListVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.service.SystemEnvironmentManagementService;
import com.cjw.reactivecommunityproject.web.system.environment_management.validation.SystemEnvironmentManagementValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/system/environment-management")
public class SystemEnvironmentManagementController {
    private final SystemEnvironmentManagementService systemEnvironmentManagementService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<SystemEnvironmentManagementListEntity>>> readEnvironmentList(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "value", required = false) String value,
            @RequestParam(value = "category", required = false) String category,

            @RequestParam(value = "start-date", required = false) String startDate,
            @RequestParam(value = "end-date", required = false) String endDate,
            @RequestParam("page-number") Integer pageNumber,
            @RequestParam("page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(systemEnvironmentManagementService.readEnvironmentManagementList(SystemEnvironmentManagementListVO.builder()
                        .id(id)
                        .type(type)
                        .value(value)
                        .category(category)
                        .startDate(DateUtils.convert(startDate, DateUtils.yyyy_MM_dd))
                        .endDate(DateUtils.convert(endDate, DateUtils.yyyy_MM_dd))
                        .build()
                , PaginationRequestVO.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()));
    }

    @GetMapping("/{env_id}")
    public ResponseEntity<RestResponseVO<SystemEnvironmentManagementDetailEntity>> readEnvironmentManagementDetail(
            @PathVariable("env_id") String id
    ) {
        return ResponseEntity.ok(systemEnvironmentManagementService.readDetail(id));
    }

    @PostMapping
    public ResponseEntity<RestResponseVO<Void>> create(
            @RequestBody @Validated(SystemEnvironmentManagementValidationGroup.Create.class) SystemEnvironmentManagementCreateVO systemEnvironmentManagementCreateVO
    ) {
        return ResponseEntity.ok(systemEnvironmentManagementService.create(systemEnvironmentManagementCreateVO));
    }

    @DeleteMapping("/{env_id}")
    public ResponseEntity<RestResponseVO<Void>> remove(
            @PathVariable("env_id") String id
    ){
        return ResponseEntity.ok(systemEnvironmentManagementService.remove(id));
    }
}
