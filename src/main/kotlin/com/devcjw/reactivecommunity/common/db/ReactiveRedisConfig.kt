package com.devcjw.reactivecommunity.common.db

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class ReactiveRedisConfig {
    @Bean
    @Primary
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    fun reactiveRedisTemplate(factory: ReactiveRedisConnectionFactory, objectMapper: ObjectMapper): ReactiveRedisTemplate<String, Any> {
        val serializer = GenericJackson2JsonRedisSerializer(objectMapper)
        val serializationContext = RedisSerializationContext
            .newSerializationContext<String, Any>(StringRedisSerializer())
            .value(serializer)
            .build()
        return ReactiveRedisTemplate(factory, serializationContext)
    }
}