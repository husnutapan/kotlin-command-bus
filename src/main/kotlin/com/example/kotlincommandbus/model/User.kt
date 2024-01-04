package com.example.kotlincommandbus.model

import java.util.UUID

data class User(
    val id: UUID, val fullName: String, val age: Int
)
