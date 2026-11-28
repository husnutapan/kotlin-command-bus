package com.example.kotlincommandbus.application.healthcheck

import com.example.kotlincommandbus.config.Handler
import com.example.kotlincommandbus.config.Request
import org.springframework.stereotype.Component

data class HealthStatus(val status: String)

class HealthCheckQuery : Request<HealthStatus>

@Component
class HealthCheckUseCase : Handler<HealthStatus, HealthCheckQuery> {

    override fun handle(request: HealthCheckQuery): HealthStatus {
        return HealthStatus("UP")
    }
}
