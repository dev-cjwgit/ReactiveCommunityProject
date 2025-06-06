package com.cjw.reactivecommunityproject.server.elasticsearch.log.exception.model;

import java.time.ZonedDateTime;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder
@Document(indexName = "log-exception")
public record ElasticsearchLogExceptionDocument(
        @Id
        String inquiryNumber
        , String message
        , String stackTrace
        , @Field(type = FieldType.Date, format = DateFormat.date_time)
        ZonedDateTime timestamp
        , Long index
) {
}
