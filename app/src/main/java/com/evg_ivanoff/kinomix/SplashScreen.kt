package com.evg_ivanoff.kinomix

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.evg_ivanoff.kinomix.models.FavoriteFilmsViewModel
import com.evg_ivanoff.kinomix.models.FavoriteFilmsViewModelFactory

class SplashScreen : AppCompatActivity() {

    private val favoriteFilmViewModel: FavoriteFilmsViewModel by viewModels {
        FavoriteFilmsViewModelFactory((application as MyApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //это тут для того чтобы проинициализироваться
        favoriteFilmViewModel.allFavoriteFilms.observe(this, {
            it?.let { }
        })

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}