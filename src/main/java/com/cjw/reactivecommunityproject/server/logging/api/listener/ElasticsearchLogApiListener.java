package com.cjw.reactivecommunityproject.server.logging.api.listener;

import com.cjw.reactivecommunityproject.server.logging.api.model.ElasticsearchLogApiDocument;
import com.cjw.reactivecommunityproject.server.logging.api.service.ElasticsearchLogApiService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticsearchLogApiListener {
    private final ElasticsearchLogApiService elasticsearchLogApiService;

    @Async
    @EventListener(ElasticsearchLogApiDocument.class)
    public void listener(@NonNull ElasticsearchLogApiDocument document){
        log.info("ElasticsearchLogApiListener.listener() : start");
        var result = elasticsearchLogApiService.insert(document);
        log.info("ElasticsearchLogApiListener.listener() : end -> {}", result);
    }
}
