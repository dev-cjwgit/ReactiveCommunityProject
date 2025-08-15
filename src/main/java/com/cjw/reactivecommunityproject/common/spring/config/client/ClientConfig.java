package com.cjw.reactivecommunityproject.common.spring.config.client;


import com.cjw.reactivecommunityproject.common.spring.config.properties.RcProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Slf4j
@Configuration
@EnableConfigurationProperties(RcProperties.class)
public class ClientConfig {

    private ExchangeFilterFunction logFilter() {
        return (req, next) -> {
            long startNanos = System.nanoTime();
            log.debug("[WebClient] => {}, {}", req.method(), req.url());

            return next.exchange(req)
                    .doOnNext(res -> {
                        long tookMs = (System.nanoTime() - startNanos) / 1_000_000;
                        log.debug("[WebClient] <= {}, {} ({} ms)%n", res.statusCode(), req.url(), tookMs);
                    });
        };
    }

    @Bean
    public WebClientCustomizer webClientCustomizer(RcProperties rcProperties) {
        return builder -> {
            var provider = ConnectionProvider.builder("global-http")
                    .maxConnections(rcProperties.http().pool().maxConnections())
                    .maxIdleTime(rcProperties.http().pool().maxIdleTime())
                    .pendingAcquireMaxCount(rcProperties.http().pool().pendingAcquireMaxCount())
                    .build();

            HttpClient httpClient = HttpClient.create(provider)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) rcProperties.http().timeouts().connect().toMillis())
                    .responseTimeout(rcProperties.http().timeouts().response())
                    .doOnConnected(conn -> conn
                            .addHandlerLast(new ReadTimeoutHandler(rcProperties.http().timeouts().read().toMillis(), TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(rcProperties.http().timeouts().write().toMillis(), TimeUnit.MILLISECONDS))
                    );

            builder.clientConnector(new ReactorClientHttpConnector(httpClient))
                    .codecs(c -> c.defaultCodecs().maxInMemorySize((int) rcProperties.http().codecs().maxInMemorySize().toBytes()))
                    .filter(this.logFilter());
        };
    }

    @Bean
    public RestClientCustomizer restClientCustomizer(RcProperties rc) {
        return builder -> {
            var connCfg = ConnectionConfig.custom()
                    .setConnectTimeout(Timeout.ofMilliseconds(rc.http().timeouts().connect().toMillis()))
                    .setSocketTimeout(Timeout.ofMilliseconds(rc.http().timeouts().read().toMillis()))
                    .build();

            var cm = PoolingHttpClientConnectionManagerBuilder.create()
                    .setDefaultConnectionConfig(connCfg)
                    .setMaxConnTotal(rc.http().pool().maxConnections())
                    .setMaxConnPerRoute(rc.http().pool().maxConnections())
                    .build();

            var reqCfg = RequestConfig.custom()
                    .setResponseTimeout(Timeout.ofMilliseconds(rc.http().timeouts().response().toMillis()))
                    .setConnectionRequestTimeout(Timeout.ofMilliseconds(rc.http().timeouts().request().toMillis()))
                    .build();

            CloseableHttpClient apache = HttpClients.custom()
                    .setConnectionManager(cm)
                    .setDefaultRequestConfig(reqCfg)
                    .evictIdleConnections(TimeValue.ofMilliseconds(rc.http().pool().maxIdleTime().toMillis()))
                    .evictExpiredConnections()
                    .build();

            ClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory(apache);
            builder.requestFactory(rf);
        };
    }
}
