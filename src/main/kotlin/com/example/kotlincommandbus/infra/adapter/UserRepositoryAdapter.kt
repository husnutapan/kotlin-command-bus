package com.example.kotlincommandbus.infra.adapter

import com.example.kotlincommandbus.infra.port.UserPort
import com.example.kotlincommandbus.model.User
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserRepositoryAdapter: UserPort {

    private val userStore = mutableListOf<User>()

    override fun save(user: User): User {
        userStore.add(user)
        return user
    }

    override fun findAll(): List<User> {
        return userStore
    }

    override fun findById(id: UUID): User? {
        return userStore.find { it.id == id }
    }
}
