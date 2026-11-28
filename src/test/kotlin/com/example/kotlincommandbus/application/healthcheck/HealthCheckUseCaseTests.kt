package com.example.kotlincommandbus.application.healthcheck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HealthCheckUseCaseTests {

    @Test
    fun `returns UP status`() {
        val useCase = HealthCheckUseCase()

        assertThat(useCase.handle(HealthCheckQuery())).isEqualTo(HealthStatus("UP"))
    }
}
