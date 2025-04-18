package com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.service.impl;

import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.repository.ElasticsearchLogExceptionRepository;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.service.ElasticsearchLogExceptionService;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElasticsearchLogExceptionServiceImpl implements ElasticsearchLogExceptionService {
    private final ElasticsearchLogExceptionRepository elasticsearchLogExceptionRepository;

    @Override
    public ElasticsearchLogExceptionDocument insert(ElasticsearchLogExceptionDocument elasticsearchLogExceptionDocument) {
        return elasticsearchLogExceptionRepository.save(elasticsearchLogExceptionDocument);
    }

    @Override
    public ElasticsearchLogExceptionDocument selectExceptionLogByInquiryNumber(String inquiryNumber) {
        return elasticsearchLogExceptionRepository.findByInquiryNumber(inquiryNumber);
    }

    @Override
    public List<ElasticsearchLogExceptionDocument> selectList(ZonedDateTime startDate, ZonedDateTime endDate) {
        return elasticsearchLogExceptionRepository.findByTimestampBetween(startDate, endDate);
    }


}
