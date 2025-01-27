package com.cjw.reactivecommunityproject.common.spring.config.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .findAndRegisterModules()
                .enable(SerializationFeature.INDENT_OUTPUT) // 출력 Json 을 들여쓰기 처리

                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // 날짜를 ISO-8601 형식으로 직렬화
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS) // 빈 객체를 직렬화 시 오류 발생 방지
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) // 알 수 없는 속성을 무시

                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE) // 스네이크 케이스 자동 맵핑
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

                .registerModule(new JavaTimeModule())
                .registerModule(new ParameterNamesModule());
    }
}
