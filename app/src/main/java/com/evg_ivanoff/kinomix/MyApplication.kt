package com.evg_ivanoff.kinomix

import android.app.Application
import com.evg_ivanoff.kinomix.models.FavoriteFilmsViewModel
import com.evg_ivanoff.kinomix.room.FavoriteFilmsDatabase
import com.evg_ivanoff.kinomix.room.FavoriteFilmsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication: Application() {
    val database by lazy {
        FavoriteFilmsDatabase.getDatabase(this)
    }
    val repository by lazy {
        FavoriteFilmsRepository(database.favFilmDao())
    }
}