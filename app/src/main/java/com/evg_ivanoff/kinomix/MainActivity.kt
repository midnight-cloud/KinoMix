package com.evg_ivanoff.kinomix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.evg_ivanoff.kinomix.databinding.ActivityMainBinding
import com.evg_ivanoff.kinomix.fragments.FavoritesFragment
import com.evg_ivanoff.kinomix.fragments.ListFragment
import com.evg_ivanoff.kinomix.fragments.SearchFragment
import com.evg_ivanoff.kinomix.models.FilmViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataModel: FilmViewModel by viewModels()
    private var activityRestored = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRestored = savedInstanceState?.getBoolean("RESTORED") != null
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (activityRestored == false) {
            startFragment(SearchFragment(), R.id.search_fragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.favorites -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.search_fragment, FavoritesFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putBoolean("RESTORED", true)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun startFragment(fragment: Fragment, id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(id, fragment)
            .commit()
    }
}