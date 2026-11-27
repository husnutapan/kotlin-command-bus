package com.example.kotlincommandbus.application.listusers

import com.example.kotlincommandbus.infra.adapter.UserRepositoryAdapter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ListUsersUseCaseTests {

    @Test
    fun `returns empty list when no users are stored`() {
        val useCase = ListUsersUseCase(UserRepositoryAdapter())

        assertThat(useCase.handle(ListUsersQuery())).isEmpty()
    }
}
