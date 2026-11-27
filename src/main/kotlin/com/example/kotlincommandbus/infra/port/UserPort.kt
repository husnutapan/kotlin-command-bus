package com.example.kotlincommandbus.infra.port

import com.example.kotlincommandbus.model.User
import java.util.UUID

interface UserPort {
    fun save(user: User): User
    fun findAll(): List<User>
    fun findById(id: UUID): User?
}
