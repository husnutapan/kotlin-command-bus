package com.example.kotlincommandbus.config

interface Handler<RES, REQ : Request<RES>> {

    fun handle(request: REQ): RES
}
