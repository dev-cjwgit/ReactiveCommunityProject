package com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.ZonedDateTime;

@Builder
@Document(indexName = "log-exception")
public record ElasticsearchLogExceptionDocument(
        @Id
        String inquiryNumber,
        String message,
        String stackTrace,
        ZonedDateTime timestamp
) {
}
