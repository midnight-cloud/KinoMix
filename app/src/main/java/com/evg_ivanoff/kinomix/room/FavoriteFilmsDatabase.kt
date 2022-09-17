package com.evg_ivanoff.kinomix.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evg_ivanoff.kinomix.Film

@Database(entities = arrayOf(Film::class), version = 1, exportSchema = false)
public abstract class FavoriteFilmsDatabase : RoomDatabase() {
    abstract fun favFilmDao(): FilmDao
    companion object {
        @Volatile
        private var INSTANCE: FavoriteFilmsDatabase? = null
        fun getDatabase(context: Context): FavoriteFilmsDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteFilmsDatabase::class.java,
                    "favorite_film_database"
                )
//                    .addCallback(FavoriteFilmsDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
//    private class FavoriteFilmsDatabaseCallback(private val scope:CoroutineScope): RoomDatabase.Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    var filmDao = database.favFilmDao()
//
//                }
//            }
//        }
//    }

}