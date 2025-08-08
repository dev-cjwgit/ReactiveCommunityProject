package com.cjw.reactivecommunityproject.server.elasticsearch.log.api.model;

import java.time.ZonedDateTime;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder
@Document(indexName = "log-api")
public record ElasticsearchLogApiDocument(
        @Id
        Long uid
        , String url
        , @Field(type = FieldType.Date, format = DateFormat.date_time)
        ZonedDateTime timestamp
) {
}
