package com.example.kotlincommandbus.application.getuser

import com.example.kotlincommandbus.config.Handler
import com.example.kotlincommandbus.config.Request
import com.example.kotlincommandbus.infra.port.UserPort
import com.example.kotlincommandbus.model.User
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

data class GetUserByIdQuery(
    val id: UUID
) : Request<User>

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(id: UUID) : RuntimeException("User $id not found")

@Component
class GetUserByIdUseCase(
    private val userPort: UserPort
) : Handler<User, GetUserByIdQuery> {

    override fun handle(request: GetUserByIdQuery): User {
        return userPort.findById(request.id) ?: throw UserNotFoundException(request.id)
    }
}
