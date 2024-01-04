package com.example.kotlincommandbus.config.decorator

import com.example.kotlincommandbus.config.Bus
import com.example.kotlincommandbus.config.Registry
import com.example.kotlincommandbus.config.Request
import org.springframework.stereotype.Component

@Component
class LoggingBus(private val registry: Registry) : Bus {
    override fun <RES, REQ : Request<RES>> execute(request: REQ): RES {
        println("starting request for ${request.javaClass}")
        val start = System.currentTimeMillis()
        return try {
            registry.get(request.javaClass).handle(request)
        } finally {
            val time = System.currentTimeMillis() - start
            println("finished request for ${request.javaClass} in $time ms")
        }
    }
}
