package com.cjw.reactivecommunityproject.web.system.environment_management.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.request.SystemEnvironmentManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.request.SystemEnvironmentManagementListVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.request.SystemEnvironmentManagementModifyVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.service.SystemEnvironmentManagementService;
import com.cjw.reactivecommunityproject.web.system.environment_management.validation.SystemEnvironmentManagementValidationGroup;
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
@RequestMapping("/rest/system/environment-management")
public class SystemEnvironmentManagementController {
    private final SystemEnvironmentManagementService systemEnvironmentManagementService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<SystemEnvironmentManagementListEntity>>> readEnvironmentList(
            @RequestParam(value = "region", required = false) String region
            , @RequestParam(value = "id", required = false) String id
            , @RequestParam(value = "type", required = false) String type
            , @RequestParam(value = "value", required = false) String value
            , @RequestParam(value = "category", required = false) String category
            , @RequestParam(value = "start-date", required = false) String startDate
            , @RequestParam(value = "end-date", required = false) String endDate
            , @RequestParam("page-number") Integer pageNumber
            , @RequestParam("page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(systemEnvironmentManagementService.readEnvironmentManagementList(SystemEnvironmentManagementListVO.builder()
                        .region(region)
                        .id(id)
                        .type(type)
                        .value(value)
                        .category(category)
                        .startDate(DateUtils.convertStringDateToZonedDateTime(startDate, DateUtils.YYYY_MM_DD))
                        .endDate(DateUtils.convertStringDateToZonedDateTime(endDate, DateUtils.YYYY_MM_DD))
                        .build()
                , PaginationOffsetRequestVO.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()));
    }

    @GetMapping("/{env_region}/{env_id}")
    public ResponseEntity<RestResponseVO<SystemEnvironmentManagementDetailEntity>> readEnvironmentManagementDetail(
            @PathVariable("env_region") String region
            , @PathVariable("env_id") String id
    ) {
        return ResponseEntity.ok(systemEnvironmentManagementService.detail(region, id));
    }

    @PostMapping
    public ResponseEntity<RestResponseVO<Void>> create(
            @RequestBody @Validated(SystemEnvironmentManagementValidationGroup.Create.class) SystemEnvironmentManagementCreateVO systemEnvironmentManagementCreateVO
    ) {
        return ResponseEntity.ok(systemEnvironmentManagementService.create(systemEnvironmentManagementCreateVO));
    }

    @DeleteMapping("/{env_region}/{env_id}")
    public ResponseEntity<RestResponseVO<Void>> remove(
            @PathVariable("env_region") String region
            , @PathVariable("env_id") String id
    ) {
        return ResponseEntity.ok(systemEnvironmentManagementService.remove(region,id));
    }

    @PatchMapping
    public ResponseEntity<RestResponseVO<Void>> modify(
            @RequestBody @Validated(SystemEnvironmentManagementValidationGroup.Modify.class) SystemEnvironmentManagementModifyVO systemEnvironmentManagementModifyVO
    ) {
        return ResponseEntity.ok(systemEnvironmentManagementService.modify(systemEnvironmentManagementModifyVO));
    }
}
