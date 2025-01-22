package com.cjw.reactivecommunityproject.web.system.role_management.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementListVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementModifyVO;
import com.cjw.reactivecommunityproject.web.system.role_management.service.SystemRoleManagementService;
import com.cjw.reactivecommunityproject.web.system.role_management.validation.SystemRoleManagementValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/system/role-management")
public class SystemRoleManagementController {
    private final SystemRoleManagementService systemRoleManagementService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<SystemRoleManagementListEntity>>> readRoleManagementList(
            @RequestParam(value = "uid", required = false) Long uid,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "start-date", required = false) String startDate,
            @RequestParam(value = "end-date", required = false) String endDate,
            @RequestParam("page-number") Integer pageNumber,
            @RequestParam("page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(systemRoleManagementService.readRoleManagementList(
                SystemRoleManagementListVO.builder()
                        .uid(uid)
                        .name(name)
                        .startDate(DateUtils.convert(startDate, DateUtils.yyyy_MM_dd))
                        .endDate(DateUtils.convert(endDate, DateUtils.yyyy_MM_dd))
                        .build(),
                PaginationRequestVO.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<RestResponseVO<SystemRoleManagementDetailEntity>> readDetail(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(systemRoleManagementService.readDetail(uid));
    }

    @PostMapping
    public ResponseEntity<RestResponseVO<Void>> create(
            @RequestBody @Validated(SystemRoleManagementValidationGroup.Create.class) SystemRoleManagementCreateVO systemRoleManagementCreateVO
    ) {
        return ResponseEntity.ok(systemRoleManagementService.create(systemRoleManagementCreateVO));
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<RestResponseVO<Void>> remove(
            @PathVariable("uid") Integer uid
    ) {
        return ResponseEntity.ok(systemRoleManagementService.remove(uid));
    }

    @PatchMapping
    public ResponseEntity<RestResponseVO<Void>> modify(
            @RequestBody @Validated(SystemRoleManagementValidationGroup.Modify.class) SystemRoleManagementModifyVO systemRoleManagementModifyVO
    ) {
        return ResponseEntity.ok(systemRoleManagementService.modify(systemRoleManagementModifyVO));
    }
}
