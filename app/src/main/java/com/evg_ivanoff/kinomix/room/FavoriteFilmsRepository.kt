package com.evg_ivanoff.kinomix.room

import androidx.annotation.WorkerThread
import com.evg_ivanoff.kinomix.Film
import kotlinx.coroutines.flow.Flow

class FavoriteFilmsRepository(private val filmDao: FilmDao) {

    val allFavoriteFilms: Flow<List<Film>> = filmDao.getAllFavoriteFilms()
    @Suppress("RedunantSuspendModifier")
    @WorkerThread
    suspend fun insert(film: Film) {
        filmDao.insert(film)
    }

    @Suppress("RedunantSuspendModifier")
    @WorkerThread
    suspend fun delete(film: Film) {
        filmDao.delete(film)
    }
}