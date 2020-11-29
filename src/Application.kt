package com.kuckjwi.ktor

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    // install
    install(ContentNegotiation) {
        // jackson
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    // route
    routing {
        get("/headers") {
            call.respond(mapOf(
                "headers" to call.request.headers.toMap()
            ))
        }
        get("/query-params") {
            call.respond(
                mapOf(
                    "query-params" to call.request.queryParameters.toMap()
                )
            )
        }
    }
}
