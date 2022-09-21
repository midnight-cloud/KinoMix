package com.evg_ivanoff.kinomix.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.evg_ivanoff.kinomix.*
import com.evg_ivanoff.kinomix.databinding.FragmentSearchBinding
import com.evg_ivanoff.kinomix.models.FilmListAdapter
import com.evg_ivanoff.kinomix.models.FilmViewModel
import com.evg_ivanoff.kinomix.retrofit.Common
import com.evg_ivanoff.kinomix.retrofit.RetrofitServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment(), FilmListAdapter.Listener {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var mService: RetrofitServices
    private val filmVM: FilmViewModel by activityViewModels()
    private lateinit var adapter: FilmListAdapter

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvFilmList.layoutManager = LinearLayoutManager(requireContext())
        adapter = FilmListAdapter(this@SearchFragment)
        binding.rvFilmList.adapter = adapter
        filmVM.filmList.observe(activity as LifecycleOwner, {
            it?.let { adapter.refresh(it) }
        })

        navController = view.findNavController()


        binding.floatFavorite.setOnClickListener {
            navController.navigate(R.id.action_searchFragment_to_favoritesFragment)
        }

        binding.btnSearch.setOnClickListener {
            mService = Common.retrofitServices
            val film = binding.tvSearchField.text.trim().toString()
            when(film) {
                "" -> {
                    Toast.makeText(context, "Empty field!", Toast.LENGTH_SHORT).show()
                    adapter.refresh(listOf())
                }
                else -> {
                    mService.getFilmListByName(
                        Common.API_KEY,
                        film
                    )
                        .enqueue(object : Callback<FilmListItem> {
                            override fun onResponse(
                                call: Call<FilmListItem>,
                                response: Response<FilmListItem>
                            ) {
                                filmVM.filmList.value = response.body()!!.search
                                Toast.makeText(
                                    context,
                                    "Find ${response.body()!!.search.size} results",
                                    Toast.LENGTH_SHORT
                                ).show()
                                filmVM.filmList.observe(activity as LifecycleOwner, {
                                    it?.let { adapter.refresh(it) }
                                })
                            }
                            override fun onFailure(call: Call<FilmListItem>, t: Throwable) {
                                Toast.makeText(context, "No network connection", Toast.LENGTH_SHORT).show()
                            }
                        })
                }
            }

        }
    }

    override fun onItemClick(item: FilmListItemDetail) {
        if (item.imdbID != null) {
            mService = Common.retrofitServices
            mService.getFilmByFilmID(
                Common.API_KEY,
                item.imdbID.toString()
            )
                .enqueue(object : Callback<Film> {
                    override fun onResponse(call: Call<Film>, response: Response<Film>) {
                        filmVM.filmDetail.value = response.body()
                        navController.navigate(R.id.action_searchFragment_to_oneFilmFragment2)
                    }

                    override fun onFailure(call: Call<Film>, t: Throwable) {
                        Toast.makeText(context, "No network connection", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}