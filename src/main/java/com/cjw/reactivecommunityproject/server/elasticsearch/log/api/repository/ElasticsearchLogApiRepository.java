package com.cjw.reactivecommunityproject.server.elasticsearch.log.api.repository;

import com.cjw.reactivecommunityproject.server.elasticsearch.log.api.model.ElasticsearchLogApiDocument;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchLogApiRepository extends ElasticsearchRepository<ElasticsearchLogApiDocument, Long> {
    ElasticsearchLogApiDocument findByUid(Long uid);

    List<ElasticsearchLogApiDocument> findByIp(String ip);

    List<ElasticsearchLogApiDocument> findByRequestTimestampBetween(ZonedDateTime startDate, ZonedDateTime endDate);

    List<ElasticsearchLogApiDocument> findByResponseTimestampBetween(ZonedDateTime startDate, ZonedDateTime endDate);
}
