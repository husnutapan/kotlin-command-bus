package com.example.kotlincommandbus.controller

import com.example.kotlincommandbus.application.saveuser.SaveUserCommand
import com.example.kotlincommandbus.config.decorator.LoggingBus
import com.example.kotlincommandbus.model.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    private val loggingBus: LoggingBus
) {
    @PostMapping
    fun create(@RequestBody saveUserCommand: SaveUserCommand): User {
        return loggingBus.execute(saveUserCommand)
    }
}
