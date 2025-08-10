package com.cjw.reactivecommunityproject.server.cache.manage.load.publisher;


import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetVO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheManageLoadPublisher {
    private final StreamBridge streamBridge;

    public boolean publishReset(CacheManageResetVO payload) {
        return streamBridge.send("cacheManageLoadProducer-out-0", MessageBuilder.withPayload(payload).build());
    }

}
