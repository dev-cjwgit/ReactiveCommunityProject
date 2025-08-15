package com.cjw.reactivecommunityproject.server.logging.exception.service.impl;

import com.cjw.reactivecommunityproject.server.logging.exception.model.ElasticsearchLogExceptionDocument;
import com.cjw.reactivecommunityproject.server.logging.exception.repository.ElasticsearchLogExceptionRepository;
import com.cjw.reactivecommunityproject.server.logging.exception.service.ElasticsearchLogExceptionService;
import jakarta.annotation.Nullable;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElasticsearchLogExceptionServiceImpl implements ElasticsearchLogExceptionService {
    private final ElasticsearchLogExceptionRepository elasticsearchLogExceptionRepository;

    @Override
    public ElasticsearchLogExceptionDocument insert(@NonNull ElasticsearchLogExceptionDocument elasticsearchLogExceptionDocument) {
        return elasticsearchLogExceptionRepository.save(elasticsearchLogExceptionDocument);
    }

    @Nullable
    @Override
    public ElasticsearchLogExceptionDocument selectExceptionLogByInquiryNumber(@NonNull String inquiryNumber) {
        return elasticsearchLogExceptionRepository.findByInquiryNumber(inquiryNumber);
    }

    @Nullable
    @Override
    public List<ElasticsearchLogExceptionDocument> selectList(@NonNull ZonedDateTime startDate, @NonNull ZonedDateTime endDate) {
        return elasticsearchLogExceptionRepository.findByTimestampBetween(startDate, endDate);
    }
}
