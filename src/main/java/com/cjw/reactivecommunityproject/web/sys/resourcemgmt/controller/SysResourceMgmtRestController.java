package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtCreateVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtModifyVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtReadListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service.SysResourceMgmtRestService;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.validation.SysResourceValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/sys/resource-mgmt")
public class SysResourceMgmtRestController {
    private final SysResourceMgmtRestService sysResourceMgmtRestService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<SysResourceMgmtListVO>>> readResourceMgmtList(
            @RequestParam(value = "start-date", required = false) String startDate,
            @RequestParam(value = "end-date", required = false) String endDate,
            @RequestParam("page-number") Integer pageNumber,
            @RequestParam("page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(sysResourceMgmtRestService.readResourceMgmtList(SysResourceMgmtReadListVO.builder()
                        .startDate(DateUtils.convert(startDate, DateUtils.yyyy_MM_dd))
                        .endDate(DateUtils.convert(endDate, DateUtils.yyyy_MM_dd))
                        .build()
                , PaginationRequestVO.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<RestResponseVO<SysResourceMgmtDetailVO>> readDetail(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(sysResourceMgmtRestService.readDetail(uid));
    }

    @PostMapping
    public ResponseEntity<RestResponseVO<Void>> create(
            @RequestBody @Validated(SysResourceValidationGroup.Create.class) SysResourceMgmtCreateVO sysResourcemgmtCreateVO
    ) {
        return ResponseEntity.ok(sysResourceMgmtRestService.create(sysResourcemgmtCreateVO));
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<RestResponseVO<Void>> remove(
            @PathVariable("uid") Long uid
    ) {
        return ResponseEntity.ok(sysResourceMgmtRestService.remove(uid));
    }

    @PatchMapping
    public ResponseEntity<RestResponseVO<Void>> modify(
            @RequestBody SysResourceMgmtModifyVO sysResourceMgmtModifyVO
    ) {
        return ResponseEntity.ok(sysResourceMgmtRestService.modify(sysResourceMgmtModifyVO));
    }
}

