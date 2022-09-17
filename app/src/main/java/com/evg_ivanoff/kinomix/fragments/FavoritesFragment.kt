package com.evg_ivanoff.kinomix.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.FilmListItemDetail
import com.evg_ivanoff.kinomix.MyApplication
import com.evg_ivanoff.kinomix.R
import com.evg_ivanoff.kinomix.databinding.FragmentFavoritesBinding
import com.evg_ivanoff.kinomix.models.*

class FavoritesFragment : Fragment(), FavoritesListAdapter.Listener {

    private lateinit var binding: FragmentFavoritesBinding
    private val filmVM: FilmViewModel by activityViewModels()

    private val favoriteFilmViewModel: FavoriteFilmsViewModel by activityViewModels {
        FavoriteFilmsViewModelFactory((requireActivity().application as MyApplication).repository)
    }

    private lateinit var adapter: FavoritesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvFavorites.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavoritesListAdapter(this@FavoritesFragment)
        binding.rvFavorites.adapter = adapter
        favoriteFilmViewModel.allFavoriteFilms.observe(activity as LifecycleOwner, {
            it?.let {
                adapter.refresh(it)
            }
        })
    }

    override fun onItemClick(item: Film) {
        filmVM.filmDetail.value = item
        launchFragment(OneFilmFragment())
    }

    private fun launchFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.search_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}