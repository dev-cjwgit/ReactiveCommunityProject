package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceManagementCreateVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceManagementModifyVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceManagementListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service.SysResourceManagementService;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.validation.SysResourceValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/sys/resource-mgmt")
public class SysResourceManagementController {
    private final SysResourceManagementService sysResourceManagementService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementListVO>>> readResourceMgmtList(
            @RequestParam(value = "start-date", required = false) String startDate,
            @RequestParam(value = "end-date", required = false) String endDate,
            @RequestParam("page-number") Integer pageNumber,
            @RequestParam("page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(sysResourceManagementService.readResourceMgmtList(SysResourceManagementListVO.builder()
                        .startDate(DateUtils.convert(startDate, DateUtils.yyyy_MM_dd))
                        .endDate(DateUtils.convert(endDate, DateUtils.yyyy_MM_dd))
                        .build()
                , PaginationRequestVO.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<RestResponseVO<SysResourceManagementDetailVO>> readDetail(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(sysResourceManagementService.readDetail(uid));
    }

    @PostMapping
    public ResponseEntity<RestResponseVO<Void>> create(
            @RequestBody @Validated(SysResourceValidationGroup.Create.class) SysResourceManagementCreateVO sysResourcemgmtCreateVO
    ) {
        return ResponseEntity.ok(sysResourceManagementService.create(sysResourcemgmtCreateVO));
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<RestResponseVO<Void>> remove(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(sysResourceManagementService.remove(uid));
    }

    @PatchMapping
    public ResponseEntity<RestResponseVO<Void>> modify(
            @RequestBody SysResourceManagementModifyVO sysResourceManagementModifyVO
    ) {
        return ResponseEntity.ok(sysResourceManagementService.modify(sysResourceManagementModifyVO));
    }
}

