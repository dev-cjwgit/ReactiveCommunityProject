package com.cjw.reactivecommunityproject.web.log.exception.service.impl;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.service.ElasticsearchLogExceptionService;
import com.cjw.reactivecommunityproject.web.log.exception.exception.LogExceptionErrorMessage;
import com.cjw.reactivecommunityproject.web.log.exception.exception.LogExceptionException;
import com.cjw.reactivecommunityproject.web.log.exception.model.request.LogExceptionListVO;
import com.cjw.reactivecommunityproject.web.log.exception.service.LogExceptionService;
import io.micrometer.common.util.StringUtils;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogExceptionServiceImpl implements LogExceptionService {
    private final ElasticsearchLogExceptionService elasticsearchLogExceptionService;


    @Override
    public RestResponseVO<ElasticsearchLogExceptionDocument> selectLogExceptionByInquiryNumber(String inquiryNumber) {
        if (StringUtils.isBlank(inquiryNumber) || StringUtil.length(inquiryNumber) != 36) {
            throw new LogExceptionException(LogExceptionErrorMessage.INVALID_INQUIRY_NUMBER_FORMAT);
        }

        var logDetail = elasticsearchLogExceptionService.selectExceptionLogByInquiryNumber(inquiryNumber);

        if (logDetail == null) {
            throw new LogExceptionException(LogExceptionErrorMessage.NOT_FOUND_LOG_BY_INQUIRY_NUMBER);
        }

        return RestResponseVO.<ElasticsearchLogExceptionDocument>builder()
                .result(true)
                .data(logDetail)
                .build();
    }

    @Override
    public RestResponseVO<List<ElasticsearchLogExceptionDocument>> selectLogExceptionList(LogExceptionListVO logExceptionListVO) {
        if (logExceptionListVO.startDate() == null || logExceptionListVO.endDate() == null) {
            throw new LogExceptionException(LogExceptionErrorMessage.INVALID_DATE_FORMAT);
        }

        var logDetail = elasticsearchLogExceptionService.selectList(logExceptionListVO.startDate(), logExceptionListVO.endDate());

        return RestResponseVO.<List<ElasticsearchLogExceptionDocument>>builder()
                .result(true)
                .data(logDetail)
                .build();

    }
}
