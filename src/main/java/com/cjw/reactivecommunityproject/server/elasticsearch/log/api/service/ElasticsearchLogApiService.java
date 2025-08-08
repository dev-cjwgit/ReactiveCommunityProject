package com.cjw.reactivecommunityproject.server.elasticsearch.log.api.service;

import com.cjw.reactivecommunityproject.server.elasticsearch.log.api.model.ElasticsearchLogApiDocument;
import java.time.ZonedDateTime;
import java.util.List;

public interface ElasticsearchLogApiService {
    ElasticsearchLogApiDocument insert(ElasticsearchLogApiDocument elasticsearchLogApiDocument);

    ElasticsearchLogApiDocument selectByUid(Long uid);

    List<ElasticsearchLogApiDocument> selectList(ZonedDateTime startDate, ZonedDateTime endDate);
}
