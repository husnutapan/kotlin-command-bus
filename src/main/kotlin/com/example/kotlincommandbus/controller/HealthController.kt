package com.example.kotlincommandbus.controller

import com.example.kotlincommandbus.application.healthcheck.HealthCheckQuery
import com.example.kotlincommandbus.application.healthcheck.HealthStatus
import com.example.kotlincommandbus.config.decorator.LoggingBus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/health")
class HealthController(
    private val loggingBus: LoggingBus
) {
    @GetMapping
    fun health(): HealthStatus {
        return loggingBus.execute(HealthCheckQuery())
    }
}
