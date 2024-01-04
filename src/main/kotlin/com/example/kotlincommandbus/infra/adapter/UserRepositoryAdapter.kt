package com.example.kotlincommandbus.infra.adapter

import com.example.kotlincommandbus.infra.port.UserPort
import com.example.kotlincommandbus.model.User
import org.springframework.stereotype.Component

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
}
