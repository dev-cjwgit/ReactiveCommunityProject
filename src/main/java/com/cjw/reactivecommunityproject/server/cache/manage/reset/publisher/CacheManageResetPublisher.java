package com.cjw.reactivecommunityproject.server.cache.manage.reset.publisher;


import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetVO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheManageResetPublisher {
    private final StreamBridge streamBridge;

    public boolean publishReset(CacheManageResetVO payload) {
        return streamBridge.send("cacheManageResetProducer-out-0", MessageBuilder.withPayload(payload).build());
    }

}
