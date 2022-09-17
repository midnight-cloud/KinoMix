package com.evg_ivanoff.kinomix.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.evg_ivanoff.kinomix.Film
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM favorite_films_table")
    fun getAllFavoriteFilms(): Flow<List<Film>>

    @Insert
    suspend fun insert(film: Film)

    @Delete
    suspend fun delete(film: Film)
}