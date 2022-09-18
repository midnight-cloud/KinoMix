package com.evg_ivanoff.kinomix.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.evg_ivanoff.kinomix.*
import com.evg_ivanoff.kinomix.databinding.FragmentSearchBinding
import com.evg_ivanoff.kinomix.models.FilmListAdapter
import com.evg_ivanoff.kinomix.models.FilmViewModel
import com.evg_ivanoff.kinomix.retrofit.Common
import com.evg_ivanoff.kinomix.retrofit.RetrofitServices
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
    private var filmList = mutableListOf<FilmListItemDetail>()

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

        binding.floatFavorite.setOnClickListener {
            launchFragment(FavoritesFragment())
        }
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
                        Toast.makeText(context, "No network connection", Toast.LENGTH_SHORT).show()
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
                        launchFragment(OneFilmFragment())
                    }
                    override fun onFailure(call: Call<Film>, t: Throwable) {
                        Toast.makeText(context, "No network connection", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}