package com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.service;

import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;

public interface ElasticsearchLogExceptionService {
    ElasticsearchLogExceptionDocument insert(ElasticsearchLogExceptionDocument elasticsearchLogExceptionDocument);
}
