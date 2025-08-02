package com.cjw.reactivecommunityproject.server.kafka.orders.model;

public record KafkaOrdersVO(
        Long id,
        String item,
        Integer count
) {
}
