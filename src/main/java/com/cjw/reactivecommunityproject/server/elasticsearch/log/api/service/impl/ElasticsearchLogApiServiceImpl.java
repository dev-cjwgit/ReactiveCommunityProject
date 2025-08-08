package com.cjw.reactivecommunityproject.server.elasticsearch.log.api.service.impl;

import com.cjw.reactivecommunityproject.server.elasticsearch.log.api.model.ElasticsearchLogApiDocument;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.api.repository.ElasticsearchLogApiRepository;
import com.cjw.reactivecommunityproject.server.elasticsearch.log.api.service.ElasticsearchLogApiService;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElasticsearchLogApiServiceImpl implements ElasticsearchLogApiService {
    private final ElasticsearchLogApiRepository elasticsearchLogApiRepository;

    @Override
    public ElasticsearchLogApiDocument insert(ElasticsearchLogApiDocument elasticsearchLogApiDocument) {
        return elasticsearchLogApiRepository.save(elasticsearchLogApiDocument);
    }

    @Override
    public ElasticsearchLogApiDocument selectByUid(Long uid) {
        return elasticsearchLogApiRepository.findByUid(uid);
    }

    @Override
    public List<ElasticsearchLogApiDocument> selectList(ZonedDateTime startDate, ZonedDateTime endDate) {
        return elasticsearchLogApiRepository.findByTimestampBetween(startDate, endDate);
    }
}
