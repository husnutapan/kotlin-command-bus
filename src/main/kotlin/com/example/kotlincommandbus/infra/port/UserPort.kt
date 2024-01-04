package com.example.kotlincommandbus.infra.port

import com.example.kotlincommandbus.model.User

interface UserPort {
    fun save(user: User): User
    fun findAll(): List<User>
}
