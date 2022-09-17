package com.evg_ivanoff.kinomix.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import coil.load
import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.MyApplication
import com.evg_ivanoff.kinomix.R
import com.evg_ivanoff.kinomix.databinding.FragmentOneFilmBinding
import com.evg_ivanoff.kinomix.models.FavoriteFilmsViewModel
import com.evg_ivanoff.kinomix.models.FavoriteFilmsViewModelFactory
import com.evg_ivanoff.kinomix.models.FilmViewModel
import com.evg_ivanoff.kinomix.retrofit.RetrofitServices

class OneFilmFragment : Fragment() {

    private var flagFav = false
    private lateinit var film: Film
    private var favFilmsBD = listOf<Film>()

    private lateinit var binding: FragmentOneFilmBinding
    private val filmVM: FilmViewModel by activityViewModels()
    private val favoriteFilmViewModel: FavoriteFilmsViewModel by activityViewModels {
        FavoriteFilmsViewModelFactory((requireActivity().application as MyApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneFilmBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        filmVM.filmDetail.observe(activity as LifecycleOwner, {
            it?.let {
                bindFields(it)
                film = it
            }
        })

        favoriteFilmViewModel.allFavoriteFilms.observe(activity as LifecycleOwner, {
            it?.let {
                favFilmsBD = it
            }
        })

        for (i in 0..favFilmsBD.size-1){
            if (film.imdbID == favFilmsBD.get(i).imdbID){
                flagFav = true
                binding.btnFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_checked))
            }
        }

        binding.btnFavorite.setOnClickListener {
            when (flagFav) {
                true -> {
                    flagFav = false
                    binding.btnFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_unchecked))
                    favoriteFilmViewModel.delete(film)
                }
                false -> {
                    flagFav = true
                    binding.btnFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_checked))
                    favoriteFilmViewModel.insert(film)
                }
            }
        }
    }

    private fun bindFields(film: Film) {
        binding.apply {
            filmImage.load(film.poster)
            filmActor.text = film.actors
            filmAwards.text = film.awards
            filmCountry.text = film.country
            filmDirector.text = film.director
            filmGenre.text = film.genre
            filmImdbrating.text = film.imdbRating
            filmMetascore.text = film.metascore
            filmPlot.text = film.plot
            filmRating.text = film.rated
            filmReleased.text = film.released
            filmRuntime.text = film.runtime
            filmTitle.text = film.title
        }
    }
}