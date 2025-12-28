package com.kurage.lab.backendapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(): String = "Hello from Spring Boot on ECS!"

    @GetMapping("/health")
    fun health(): Map<String, String> = mapOf("status" to "UP")
}
