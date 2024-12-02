package com.cjw.reactivecommunityproject.common.spring.config.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ElasticsearchConfig {
    private final ElasticsearchProperties elasticsearchProperties;


}
