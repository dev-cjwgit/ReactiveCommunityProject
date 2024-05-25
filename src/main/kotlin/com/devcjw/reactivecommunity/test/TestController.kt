package com.devcjw.reactivecommunity.test

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
class TestController {
    @GetMapping("/func1")
    fun login(): Mono<String> {
        return Mono.just("dd")
    }
}