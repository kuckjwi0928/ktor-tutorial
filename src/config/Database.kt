package com.kuckjwi.ktor.config

import com.kuckjwi.ktor.domain.entity.Post
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.*
import io.ktor.util.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

private const val DATABASE_CONFIG_PATH = "ktor.databaseConfigPath"

@KtorExperimentalAPI
fun init() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    val dataSource = HikariDataSource(HikariConfig(config.property(DATABASE_CONFIG_PATH).getString()))
    Database.connect(dataSource)
    transaction {
        SchemaUtils.create(
            Post
        )
    }
}
