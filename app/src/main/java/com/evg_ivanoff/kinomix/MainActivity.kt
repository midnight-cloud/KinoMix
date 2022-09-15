package com.evg_ivanoff.kinomix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.evg_ivanoff.kinomix.databinding.ActivityMainBinding
import com.evg_ivanoff.kinomix.fragments.ListFragment
import com.evg_ivanoff.kinomix.fragments.SearchFragment
import com.evg_ivanoff.kinomix.models.FilmViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataModel: FilmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startFragment(SearchFragment(), R.id.search_fragment)
    }

    private fun startFragment(fragment: Fragment, id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(id, fragment)
            .commit()
    }
}