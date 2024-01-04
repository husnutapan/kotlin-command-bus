package com.example.kotlincommandbus.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.GenericTypeResolver.resolveTypeArguments
import org.springframework.stereotype.Component

@Component
class Registry @Autowired constructor(applicationContext: ApplicationContext) {

    private val providerMap: MutableMap<Class<out Request<*>>, CommandProvider<*>> = mutableMapOf()

    init {
        val beanNames = applicationContext.getBeanNamesForType(Handler::class.java)
        beanNames.forEach { register(applicationContext, it) }
    }

    fun register(applicationContext: ApplicationContext, beanName: String) {
        val handlerClazz = applicationContext.getType(beanName) as Class<Handler<*, *>>
        val generics = resolveTypeArguments(handlerClazz, Handler::class.java)!!
        val commandType = generics[1] as Class<out Request<*>>
        providerMap[commandType] = CommandProvider(applicationContext, handlerClazz)
    }

    fun <RES, REQ : Request<RES>> get(commandClazz: Class<REQ>): Handler<RES, REQ> {
        return providerMap[commandClazz]!!.get() as Handler<RES, REQ>
    }

}
