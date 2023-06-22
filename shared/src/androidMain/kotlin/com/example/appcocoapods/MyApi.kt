package com.example.appcocoapods

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual class MyApi actual constructor() {

    private val nonStrictJson = Json {
        isLenient = true
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(nonStrictJson)
        }
    }

    actual suspend fun getDuckUrl(): String {
        val response = client
            .get("https://random-d.uk/api/random").body<RandomDuck>()
        return response.duckUrl
    }

}
