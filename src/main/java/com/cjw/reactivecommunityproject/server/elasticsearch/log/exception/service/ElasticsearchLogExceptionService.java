package com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.service;

import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;
import java.time.ZonedDateTime;
import java.util.List;

public interface ElasticsearchLogExceptionService {
    ElasticsearchLogExceptionDocument insert(ElasticsearchLogExceptionDocument elasticsearchLogExceptionDocument);

    ElasticsearchLogExceptionDocument selectExceptionLogByInquiryNumber(String inquiryNumber);

    List<ElasticsearchLogExceptionDocument> selectList(ZonedDateTime startDate, ZonedDateTime endDate);
}
