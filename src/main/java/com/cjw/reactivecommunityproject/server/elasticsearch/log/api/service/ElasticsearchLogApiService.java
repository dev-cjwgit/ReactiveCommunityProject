package com.cjw.reactivecommunityproject.server.elasticsearch.log.api.service;

import com.cjw.reactivecommunityproject.server.elasticsearch.log.api.model.ElasticsearchLogApiDocument;
import java.time.ZonedDateTime;
import java.util.List;

public interface ElasticsearchLogApiService {
    ElasticsearchLogApiDocument insert(ElasticsearchLogApiDocument elasticsearchLogApiDocument);

    ElasticsearchLogApiDocument selectByUid(Long uid);

    List<ElasticsearchLogApiDocument> selectByIp(String ip);

    List<ElasticsearchLogApiDocument> selectListByRequestTimestamp(ZonedDateTime startDate, ZonedDateTime endDate);
    List<ElasticsearchLogApiDocument> selectListByResponseTimestamp(ZonedDateTime startDate, ZonedDateTime endDate);
}
