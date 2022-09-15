package com.evg_ivanoff.kinomix.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.evg_ivanoff.kinomix.R
import com.evg_ivanoff.kinomix.databinding.ActivityFilmDetailBinding
import com.evg_ivanoff.kinomix.fragments.OneFilmFragment

class FilmDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilmDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startFragment(OneFilmFragment(), R.id.detail_fragment)
    }

    private fun startFragment(fragment: Fragment, id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(id, fragment)
            .commit()
    }
}