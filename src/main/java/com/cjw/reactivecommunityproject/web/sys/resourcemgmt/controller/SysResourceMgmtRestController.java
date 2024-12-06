package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtSelectListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service.SysResourceMgmtRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/sys/resource-mgmt")
public class SysResourceMgmtRestController {
    private final SysResourceMgmtRestService sysResourceMgmtRestService;

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<SysResourceMgmtListVO>>> getResourceMgmtList(
            @RequestParam(value = "start-date", required = false) String startDate,
            @RequestParam(value = "end-date", required = false) String endDate,
            @RequestParam("page-number") Integer pageNumber,
            @RequestParam("page-size") Integer pageSize
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ZonedDateTime zonedStartDate = null;
        ZonedDateTime zonedEndDate = null;
        if (startDate != null && endDate != null) {
            LocalDate localStartDate = LocalDate.parse(startDate, formatter);
            LocalDate localEndDate = LocalDate.parse(endDate, formatter);

            zonedStartDate = localStartDate.atStartOfDay(ZoneId.of("UTC"));
            zonedEndDate = localEndDate.atStartOfDay(ZoneId.of("UTC"));
        }
        return ResponseEntity.ok(sysResourceMgmtRestService.getResourceMgmtList(SysResourceMgmtSelectListVO.builder()
                        .startDate(zonedStartDate)
                        .endDate(zonedEndDate)
                        .build()
                , PaginationRequestVO.builder()
                        .pageNumber(pageNumber)
                        .pageSize(pageSize)
                        .build()));
    }
}
