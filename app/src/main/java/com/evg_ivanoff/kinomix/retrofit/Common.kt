package com.evg_ivanoff.kinomix.retrofit

object Common {

    private val BASE_URL = "https://www.omdbapi.com/"
    val API_KEY = "9b7e5ebe"
    val retrofitServices: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}