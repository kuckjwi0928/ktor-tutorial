package com.kuckjwi.ktor.service

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.provideServices(){
     bind<PostService>() with singleton { PostService() }
}
