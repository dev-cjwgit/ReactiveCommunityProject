package com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.repository;

import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ElasticsearchLogExceptionRepository extends ElasticsearchRepository<ElasticsearchLogExceptionDocument, String> {
    ElasticsearchLogExceptionDocument findByInquiryNumber(String inquiryNumber);

    List<ElasticsearchLogExceptionDocument> findByTimestampBetween(ZonedDateTime startDate, ZonedDateTime endDate);

}
