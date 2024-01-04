package com.example.kotlincommandbus.config

import org.springframework.context.ApplicationContext

class CommandProvider<HANDLER : Handler<*, *>>(
    private var applicationContext: ApplicationContext,
    private var type: Class<HANDLER>
) {

    fun get(): HANDLER {
        return applicationContext.getBean(type)
    }
}
