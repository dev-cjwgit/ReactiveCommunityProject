package com.cjw.reactivecommunityproject.server.logging.api.service.impl;

import com.cjw.reactivecommunityproject.server.logging.api.model.ElasticsearchLogApiDocument;
import com.cjw.reactivecommunityproject.server.logging.api.repository.ElasticsearchLogApiRepository;
import com.cjw.reactivecommunityproject.server.logging.api.service.ElasticsearchLogApiService;
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
public class ElasticsearchLogApiServiceImpl implements ElasticsearchLogApiService {
    private final ElasticsearchLogApiRepository elasticsearchLogApiRepository;

    @Override
    public ElasticsearchLogApiDocument insert(@NonNull ElasticsearchLogApiDocument elasticsearchLogApiDocument) {
        return elasticsearchLogApiRepository.save(elasticsearchLogApiDocument);
    }

    @Nullable
    @Override
    public ElasticsearchLogApiDocument selectByUid(@NonNull Long uid) {
        return elasticsearchLogApiRepository.findByUid(uid);
    }

    @NonNull
    @Override
    public List<ElasticsearchLogApiDocument> selectByIp(@NonNull String ip) {
        return elasticsearchLogApiRepository.findByIp(ip);
    }

    @NonNull
    @Override
    public List<ElasticsearchLogApiDocument> selectListByRequestTimestamp(@NonNull ZonedDateTime startDate, @NonNull ZonedDateTime endDate) {
        return elasticsearchLogApiRepository.findByRequestTimestampBetween(startDate, endDate);

    }

    @NonNull
    @Override
    public List<ElasticsearchLogApiDocument> selectListByResponseTimestamp(@NonNull ZonedDateTime startDate, @NonNull ZonedDateTime endDate) {
        return elasticsearchLogApiRepository.findByResponseTimestampBetween(startDate, endDate);
    }
}
