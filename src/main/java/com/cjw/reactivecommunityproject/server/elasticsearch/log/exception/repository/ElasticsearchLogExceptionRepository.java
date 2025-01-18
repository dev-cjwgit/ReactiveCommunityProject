package com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.repository;

import com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model.ElasticsearchLogExceptionDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticsearchLogExceptionRepository extends ElasticsearchRepository<ElasticsearchLogExceptionDocument, String> {
}
