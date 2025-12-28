package com.kurage.lab.backendapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.math.sqrt

@RestController
@RequestMapping("/api/load")
class LoadController {
    @GetMapping("/cpu")
    fun cpuLoad(
        @RequestParam(defaultValue = "1000000") iterations: Int,
    ): Map<String, Any> {
        val start = System.currentTimeMillis()
        var result = 0.0
        for (i in 1..iterations) {
            result += sqrt(i.toDouble()) * sqrt(i.toDouble())
        }
        val elapsed = System.currentTimeMillis() - start
        return mapOf(
            "type" to "cpu",
            "iterations" to iterations,
            "elapsedMs" to elapsed,
            "result" to result,
        )
    }

    @GetMapping("/memory")
    fun memoryLoad(
        @RequestParam(defaultValue = "10") sizeMb: Int,
    ): Map<String, Any> {
        val start = System.currentTimeMillis()
        val data = ByteArray(sizeMb * 1024 * 1024)
        for (i in data.indices step 4096) {
            data[i] = (i % 256).toByte()
        }
        val elapsed = System.currentTimeMillis() - start
        return mapOf(
            "type" to "memory",
            "sizeMb" to sizeMb,
            "elapsedMs" to elapsed,
            "allocated" to data.size,
        )
    }

    @GetMapping("/delay")
    fun delayLoad(
        @RequestParam(defaultValue = "1000") delayMs: Long,
    ): Map<String, Any> {
        val start = System.currentTimeMillis()
        Thread.sleep(delayMs)
        val elapsed = System.currentTimeMillis() - start
        return mapOf(
            "type" to "delay",
            "requestedDelayMs" to delayMs,
            "actualElapsedMs" to elapsed,
        )
    }
}
