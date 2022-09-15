package com.evg_ivanoff.kinomix.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.evg_ivanoff.kinomix.FilmListAdapter
import com.evg_ivanoff.kinomix.FilmListItem
import com.evg_ivanoff.kinomix.FilmListItemDetail
import com.evg_ivanoff.kinomix.R
import com.evg_ivanoff.kinomix.databinding.FragmentListBinding
import com.evg_ivanoff.kinomix.databinding.FragmentSearchBinding
import com.evg_ivanoff.kinomix.models.FilmViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var listItem: List<FilmListItemDetail>
    private lateinit var adapter: FilmListAdapter
    private var size : Int = 0
    private var titles = mutableListOf<String>()

    private lateinit var binding: FragmentListBinding
    private val filmVM: FilmViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            listItem = it.getParcelable("FILM_LIST")!!
            size = it.getInt("SIZE")
            for (i in 0..size-1) {
                titles.add(it.getString("FILM_TITLE$i")!!)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TAG_FILM", titles.toString())
//        listItem = filmVM.getSearchFilmList()
        binding.rvFilmList.layoutManager = LinearLayoutManager(requireContext())
        adapter = FilmListAdapter()
        binding.rvFilmList.adapter = adapter
        filmVM.filmList.observe(activity as LifecycleOwner, {
            it?.let { adapter.refresh(it) }
        })
//        if (titles != null) {
//            binding.rvFilmList.layoutManager = LinearLayoutManager(requireContext())
//            adapter = FilmListAdapter()
//            binding.rvFilmList.adapter = adapter
//            adapter.refresh(titles)
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}