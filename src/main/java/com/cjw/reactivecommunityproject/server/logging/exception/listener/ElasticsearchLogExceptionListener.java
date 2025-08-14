package com.cjw.reactivecommunityproject.server.logging.exception.listener;

import com.cjw.reactivecommunityproject.server.logging.exception.model.ElasticsearchLogExceptionDocument;
import com.cjw.reactivecommunityproject.server.logging.exception.service.ElasticsearchLogExceptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticsearchLogExceptionListener {
    private final ElasticsearchLogExceptionService elasticsearchLogExceptionService;

    @Async
    @EventListener(ElasticsearchLogExceptionDocument.class)
    public void listener(ElasticsearchLogExceptionDocument document) {
        log.info("ElasticsearchLogExceptionListener.listener() : start");
        var result = elasticsearchLogExceptionService.insert(document);
        log.info("ElasticsearchLogExceptionListener.listener() : end -> {}", result);
    }
}
