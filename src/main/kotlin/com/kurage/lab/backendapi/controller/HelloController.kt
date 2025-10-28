package com.kurage.lab.backendapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello from Spring Boot on ECS!"
    }

    @GetMapping("/health")
    fun health(): Map<String, String> {
        return mapOf("status" to "UP")
    }

    @GetMapping("/aaa")
    fun aaa(): Map<String, String> {
        return mapOf("status" to "UP")
    }
}
