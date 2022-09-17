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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavoritesFragment : Fragment(), FavoritesListAdapter.Listener {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentFavoritesBinding
    private val filmVM: FilmViewModel by activityViewModels()

    private val favoriteFilmViewModel: FavoriteFilmsViewModel by activityViewModels {
        FavoriteFilmsViewModelFactory((requireActivity().application as MyApplication).repository)
    }

    private lateinit var adapter: FavoritesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

//        Log.d("TESTO", chota!!.get(0).title.toString())

        favoriteFilmViewModel.allFavoriteFilms.observe(activity as LifecycleOwner, {
//            Log.d("TESTO", it.toString())
            it?.let {
                adapter.refresh(it)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(item: Film) {
        Toast.makeText(context, "Uacacaca", Toast.LENGTH_SHORT).show()
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