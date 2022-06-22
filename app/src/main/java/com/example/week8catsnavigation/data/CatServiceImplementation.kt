package com.example.week8catsnavigation.data

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class CatServiceImplementation(
    private val client: HttpClient
) : CatService {

    override suspend fun getAndPrintCat(): List<CatData> {
        return client.get { url(HttpRoutes.RANDOM_CAT) }
    }
}