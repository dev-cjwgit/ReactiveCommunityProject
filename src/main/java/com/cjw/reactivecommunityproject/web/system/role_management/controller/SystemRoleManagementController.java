package com.cjw.reactivecommunityproject.web.system.role_management.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementListVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementModifyVO;
import com.cjw.reactivecommunityproject.web.system.role_management.service.SystemRoleManagementService;
import com.cjw.reactivecommunityproject.web.system.role_management.validation.SystemRoleManagementValidationGroup;
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
@RequestMapping("/rest/system/role-management")
public class SystemRoleManagementController {
    private final SystemRoleManagementService systemRoleManagementService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<SystemRoleManagementListEntity>>> readRoleManagementList(
            @RequestParam(value = "uid", required = false) Long uid
            , @RequestParam(value = "name", required = false) String name
            , @RequestParam(value = "start-date", required = false) String startDate
            , @RequestParam(value = "end-date", required = false) String endDate
            , @RequestParam("page-number") Integer pageNumber
            , @RequestParam("page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(systemRoleManagementService.readRoleManagementList(
                SystemRoleManagementListVO.builder()
                        .uid(uid)
                        .name(name)
                        .startDate(DateUtils.convertStringDateToZonedDateTime(startDate, DateUtils.YYYY_MM_DD))
                        .endDate(DateUtils.convertStringDateToZonedDateTime(endDate, DateUtils.YYYY_MM_DD))
                        .build()
                , PaginationOffsetRequestVO.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<RestResponseVO<SystemRoleManagementDetailEntity>> detail(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(systemRoleManagementService.detail(uid));
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
