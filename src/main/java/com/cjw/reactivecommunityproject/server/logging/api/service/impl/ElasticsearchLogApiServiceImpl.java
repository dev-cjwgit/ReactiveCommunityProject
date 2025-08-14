package com.cjw.reactivecommunityproject.server.logging.api.service.impl;

import com.cjw.reactivecommunityproject.server.logging.api.model.ElasticsearchLogApiDocument;
import com.cjw.reactivecommunityproject.server.logging.api.repository.ElasticsearchLogApiRepository;
import com.cjw.reactivecommunityproject.server.logging.api.service.ElasticsearchLogApiService;
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
    public List<ElasticsearchLogApiDocument> selectByIp(String ip) {
        return elasticsearchLogApiRepository.findByIp(ip);
    }

    @Override
    public List<ElasticsearchLogApiDocument> selectListByRequestTimestamp(ZonedDateTime startDate, ZonedDateTime endDate) {
        return elasticsearchLogApiRepository.findByRequestTimestampBetween(startDate, endDate);

    }

    @Override
    public List<ElasticsearchLogApiDocument> selectListByResponseTimestamp(ZonedDateTime startDate, ZonedDateTime endDate) {
        return elasticsearchLogApiRepository.findByResponseTimestampBetween(startDate, endDate);
    }
}
