package com.cjw.reactivecommunityproject.web.log.exception.controller;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.util.DateUtils;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;
import com.cjw.reactivecommunityproject.web.log.exception.model.request.LogExceptionListVO;
import com.cjw.reactivecommunityproject.web.log.exception.service.LogExceptionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/log/exception")
public class LogExceptionController {
    private final LogExceptionService logExceptionService;

    @GetMapping("/{inquiry_number}")
    public ResponseEntity<RestResponseVO<ElasticsearchLogExceptionDocument>> selectLogExceptionByInquiryNumber(
            @PathVariable("inquiry_number") String inquiryNumber
    ) {
        return ResponseEntity.ok(logExceptionService.selectLogExceptionByInquiryNumber(inquiryNumber));
    }

    @GetMapping("/list")
    public ResponseEntity<RestResponseVO<List<ElasticsearchLogExceptionDocument>>> selectLogExceptionList(
            @RequestParam(value = "start-date", required = false) String startDate,
            @RequestParam(value = "end-date", required = false) String endDate
    ) {
        return ResponseEntity.ok(logExceptionService.selectLogExceptionList(LogExceptionListVO.builder()
                .startDate(DateUtils.convert(startDate, DateUtils.YYYY_MM_DD_HH_MM_SS))
                .endDate(DateUtils.convert(endDate, DateUtils.YYYY_MM_DD_HH_MM_SS))
                .build()));
    }
}
