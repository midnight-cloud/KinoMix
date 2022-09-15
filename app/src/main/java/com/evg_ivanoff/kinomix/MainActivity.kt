package com.evg_ivanoff.kinomix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.evg_ivanoff.kinomix.databinding.ActivityMainBinding
import com.evg_ivanoff.kinomix.fragments.ListFragment
import com.evg_ivanoff.kinomix.fragments.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.search_fragment, SearchFragment())
//            .add(R.id.result_fragment, ListFragment())
            .commit()
    }
}