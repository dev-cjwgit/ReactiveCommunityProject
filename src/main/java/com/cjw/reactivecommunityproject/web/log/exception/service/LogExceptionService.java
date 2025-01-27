package com.cjw.reactivecommunityproject.web.log.exception.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;
import com.cjw.reactivecommunityproject.web.log.exception.model.request.LogExceptionListVO;

import java.util.List;

public interface LogExceptionService {
    RestResponseVO<ElasticsearchLogExceptionDocument> selectLogExceptionByInquiryNumber(String inquiryNumber);

    RestResponseVO<List<ElasticsearchLogExceptionDocument>> selectLogExceptionList(LogExceptionListVO logExceptionListVO);
}
