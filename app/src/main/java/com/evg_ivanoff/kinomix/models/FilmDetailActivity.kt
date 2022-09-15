package com.evg_ivanoff.kinomix.models

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.R
import com.evg_ivanoff.kinomix.databinding.ActivityFilmDetailBinding
import com.evg_ivanoff.kinomix.fragments.OneFilmFragment
import com.evg_ivanoff.kinomix.retrofit.Common
import com.evg_ivanoff.kinomix.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilmDetailBinding
    private lateinit var mService: RetrofitServices
    private val filmVM: FilmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imdbID = intent.getStringExtra("FILM_ID")
        Log.d("TAG_FILM", imdbID.toString())
        if (imdbID != null) {
            mService = Common.retrofitServices
            mService.getFilmByFilmID(
                Common.API_KEY,
                imdbID
            )
                .enqueue(object : Callback<Film> {
                    override fun onResponse(call: Call<Film>, response: Response<Film>) {
                        filmVM.filmDetail.value = response.body()
                        Log.d("TAG_FILM", response.body().toString())
                        startFragment(OneFilmFragment(), R.id.detail_fragment)
                    }

                    override fun onFailure(call: Call<Film>, t: Throwable) {
                        Toast.makeText(applicationContext, "404 error", Toast.LENGTH_SHORT).show()
                        Log.d("TAG_FILM", t.toString())
                    }
                })
            }
    }

    private fun startFragment(fragment: Fragment, id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(id, fragment)
            .commit()
    }
}