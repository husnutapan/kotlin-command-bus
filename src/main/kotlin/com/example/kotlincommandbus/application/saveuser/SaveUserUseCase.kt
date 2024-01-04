package com.example.kotlincommandbus.application.saveuser

import com.example.kotlincommandbus.config.Handler
import com.example.kotlincommandbus.config.Request
import com.example.kotlincommandbus.infra.port.UserPort
import com.example.kotlincommandbus.model.User
import org.springframework.stereotype.Component
import java.util.*

data class SaveUserCommand(
    val name: String,
    val surname: String,
    val age: Int
) : Request<User>

fun SaveUserCommand.toUser() = User(
    id = UUID.randomUUID(),
    fullName = this.name + this.surname,
    age = age
)


@Component
class SaveUserUseCase (
    private val userPort: UserPort
) : Handler<User, SaveUserCommand> {

    override fun handle(request: SaveUserCommand): User {
        return userPort.save(request.toUser())
    }
}
