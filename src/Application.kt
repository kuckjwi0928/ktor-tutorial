package com.kuckjwi.ktor

import com.kuckjwi.ktor.domain.dto.Post
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    // install
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
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
        post("/posts") {
            // https://youtrack.jetbrains.com/issue/KTOR-1286
            withContext(Dispatchers.IO) {
                val post = call.receive<Post>()
                call.respond(post)
            }
        }
    }
}
