package com.evg_ivanoff.kinomix.retrofit

import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.FilmListItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("/")
    fun getFilmByFilmID(
        @Query("apikey") apiKey: String,
        @Query("t") name:String
    ): Call<Film>

    @GET("/")
    fun getOneFilmListByName(
        @Query("apikey") apiKey: String,
        @Query("s") title: String): Call<FilmListItem>

    @GET("/")
    fun getFilmListByName(
        @Query("apikey") apiKey: String,
        @Query("s") title: String): Call<FilmListItem>

//    @GET("/")
//    fun getTest(
//        @Query("apikey") apiKey: String,
//        @Query("i") id: String
//    ): Call<Film>

}