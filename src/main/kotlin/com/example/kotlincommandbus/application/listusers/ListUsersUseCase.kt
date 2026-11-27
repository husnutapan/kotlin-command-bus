package com.example.kotlincommandbus.application.listusers

import com.example.kotlincommandbus.config.Handler
import com.example.kotlincommandbus.config.Request
import com.example.kotlincommandbus.infra.port.UserPort
import com.example.kotlincommandbus.model.User
import org.springframework.stereotype.Component

class ListUsersQuery : Request<List<User>>

@Component
class ListUsersUseCase(
    private val userPort: UserPort
) : Handler<List<User>, ListUsersQuery> {

    override fun handle(request: ListUsersQuery): List<User> {
        return userPort.findAll().toList()
    }
}
