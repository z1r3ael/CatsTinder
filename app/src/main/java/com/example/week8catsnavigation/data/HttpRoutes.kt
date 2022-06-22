package com.example.week8catsnavigation.data

object HttpRoutes {

    //https://api.thecatapi.com/v1/images/search?api_key=6dea088e-c561-4050-aa4d-09be4c639bf0

    private const val BASE_URL = "https://api.thecatapi.com"
    private const val API_KEY = "api_key=6dea088e-c561-4050-aa4d-09be4c639bf0"
    const val RANDOM_CAT = "$BASE_URL/v1/images/search?$API_KEY"
}