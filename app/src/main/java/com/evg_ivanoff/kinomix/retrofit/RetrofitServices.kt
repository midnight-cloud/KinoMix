package com.evg_ivanoff.kinomix.retrofit

import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.FilmListItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("{s}")
    fun getFilmByFilmID(@Query("apikey") apiKey: String): Call<Film>

    @GET("?apikey=9b7e5ebe")
    fun getFilmListByName(
//        @Query("apikey") apiKey: String,
        @Query("s") title: String): Call<MutableList<FilmListItem>>

    @GET("/")
    fun getTest(
        @Query("apikey") apiKey: String,
        @Query("i") id: String
    ): Call<Film>

}