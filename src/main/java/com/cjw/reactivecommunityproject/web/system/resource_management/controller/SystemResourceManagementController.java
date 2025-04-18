package com.cjw.reactivecommunityproject.web.system.resource_management.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SystemResourceManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SystemResourceManagementListVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SystemResourceManagementModifyVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.service.SystemResourceManagementService;
import com.cjw.reactivecommunityproject.web.system.resource_management.validation.SystemResourceManagementValidationGroup;
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
@RequestMapping("/rest/system/resource-management")
public class SystemResourceManagementController {
    private final SystemResourceManagementService systemResourceManagementService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<SystemResourceManagementListEntity>>> readResourceMgmtList(
            @RequestParam(value = "uid", required = false) Long uid,
            @RequestParam(value = "method", required = false) String method,
            @RequestParam(value = "url-pattern", required = false) String urlPattern,

            @RequestParam(value = "start-date", required = false) String startDate,
            @RequestParam(value = "end-date", required = false) String endDate,
            @RequestParam("page-number") Integer pageNumber,
            @RequestParam("page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(systemResourceManagementService.readResourceMgmtList(SystemResourceManagementListVO.builder()
                        .uid(uid)
                        .method(method)
                        .urlPattern(urlPattern)
                        .startDate(DateUtils.convert(startDate, DateUtils.YYYY_MM_DD))
                        .endDate(DateUtils.convert(endDate, DateUtils.YYYY_MM_DD))
                        .build()
                , PaginationRequestVO.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<RestResponseVO<SystemResourceManagementDetailEntity>> readDetail(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(systemResourceManagementService.readDetail(uid));
    }

    @PostMapping
    public ResponseEntity<RestResponseVO<Void>> create(
            @RequestBody @Validated(SystemResourceManagementValidationGroup.Create.class) SystemResourceManagementCreateVO sysResourcemgmtCreateVO
    ) {
        return ResponseEntity.ok(systemResourceManagementService.create(sysResourcemgmtCreateVO));
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<RestResponseVO<Void>> remove(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(systemResourceManagementService.remove(uid));
    }

    @PatchMapping
    public ResponseEntity<RestResponseVO<Void>> modify(
            @RequestBody @Validated(SystemResourceManagementValidationGroup.Modify.class) SystemResourceManagementModifyVO systemResourceManagementModifyVO
    ) {
        return ResponseEntity.ok(systemResourceManagementService.modify(systemResourceManagementModifyVO));
    }
}

