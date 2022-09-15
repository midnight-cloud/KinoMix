package com.evg_ivanoff.kinomix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import coil.load
import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.databinding.FragmentOneFilmBinding
import com.evg_ivanoff.kinomix.models.FilmViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OneFilmFragment : Fragment(){

    private var param1: String? = null
    private var param2: String? = null

    private var filmTitle: String? = null

    private lateinit var binding: FragmentOneFilmBinding
    private val filmVM: FilmViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            filmTitle = it.getString("FILM_TITLE")
        }
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
            it?.let { bindFields(it) }
        })
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OneFilmFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}