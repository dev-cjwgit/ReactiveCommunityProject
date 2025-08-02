package com.cjw.reactivecommunityproject.server.kafka.orders.functions;

import com.cjw.reactivecommunityproject.server.kafka.orders.model.KafkaOrdersVO;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class OrderFunctions {

    @Bean
    public Function<Flux<KafkaOrdersVO>, Mono<Void>> orderFunction() {
        return flux -> flux
                .doOnNext(o -> {
                    log.info(o.toString());
                })
                .then();
    }
}
