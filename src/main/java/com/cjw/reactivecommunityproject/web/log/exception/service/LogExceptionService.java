package com.cjw.reactivecommunityproject.web.log.exception.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;

public interface LogExceptionService {
    RestResponseVO<ElasticsearchLogExceptionDocument> selectLogExceptionByInquiryNumber(String inquiryNumber);
}
