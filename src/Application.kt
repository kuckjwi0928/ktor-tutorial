package com.kuckjwi.ktor

import com.kuckjwi.ktor.config.init
import com.kuckjwi.ktor.domain.dto.PostDto
import com.kuckjwi.ktor.service.PostService
import com.kuckjwi.ktor.service.provideServices
import com.zaxxer.hikari.HikariConfig
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    // init
    init()

    // install
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    // dependency injection
    di {
        provideServices()
    }

    // route
    routing {
        val postService by di().instance<PostService>()

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
                call.respond(HttpStatusCode.Created, postService.addPost(call.receive()) )
            }
        }
    }
}
