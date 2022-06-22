package com.example.week8catsnavigation.data

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

interface CatService {

    suspend fun getAndPrintCat(): List<CatData>

    companion object {
        fun create(): CatService {
            return CatServiceImplementation(
                client = HttpClient(Android) {
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                            ignoreUnknownKeys = true
                        })
                    }
                }
            )
        }
    }
}