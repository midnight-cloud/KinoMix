package com.evg_ivanoff.kinomix.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.FilmListItem
import com.evg_ivanoff.kinomix.FilmListItemDetail
import com.evg_ivanoff.kinomix.R
import com.evg_ivanoff.kinomix.databinding.FragmentSearchBinding
import com.evg_ivanoff.kinomix.models.FilmListAdapter
import com.evg_ivanoff.kinomix.models.FilmViewModel
import com.evg_ivanoff.kinomix.retrofit.Common
import com.evg_ivanoff.kinomix.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment(), FilmListAdapter.Listener {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentSearchBinding
    private lateinit var mService: RetrofitServices
    private val filmVM: FilmViewModel by activityViewModels()
    private lateinit var adapter: FilmListAdapter
    private var filmList = mutableListOf<FilmListItemDetail>()

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

        binding.btnSearch.setOnClickListener {
            mService = Common.retrofitServices
            val film = binding.tvSearchField.text.trim().toString()
            mService.getFilmListByName(
                Common.API_KEY,
                film
            )
                .enqueue(object : Callback<FilmListItem> {
                    override fun onResponse(
                        call: Call<FilmListItem>,
                        response: Response<FilmListItem>
                    ) {
                        filmList = response.body()!!.search
                        filmVM.filmList.value = response.body()!!.search
                        Toast.makeText(
                            context,
                            "Find ${response.body()!!.search.size} results",
                            Toast.LENGTH_SHORT
                        ).show()
                        adapter.refresh(filmList)
                    }

                    override fun onFailure(call: Call<FilmListItem>, t: Throwable) {
                        Toast.makeText(context, "404 error", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    private fun launchFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.search_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
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
                        Log.d("TAG_FILM", response.body().toString())
                        launchFragment(OneFilmFragment())
                    }

                    override fun onFailure(call: Call<Film>, t: Throwable) {
                        Toast.makeText(context, "404 error", Toast.LENGTH_SHORT).show()
                        Log.d("TAG_FILM", t.toString())
                    }
                })
        }
    }
}