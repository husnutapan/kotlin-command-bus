package com.example.kotlincommandbus.config

interface Bus {
    fun <RES, REQ : Request<RES>> execute(request: REQ): RES
}
