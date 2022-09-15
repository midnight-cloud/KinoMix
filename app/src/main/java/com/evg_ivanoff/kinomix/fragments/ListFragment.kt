package com.evg_ivanoff.kinomix.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.evg_ivanoff.kinomix.models.FilmListAdapter
import com.evg_ivanoff.kinomix.FilmListItemDetail
import com.evg_ivanoff.kinomix.R
import com.evg_ivanoff.kinomix.databinding.FragmentListBinding
import com.evg_ivanoff.kinomix.models.FilmDetailActivity
import com.evg_ivanoff.kinomix.models.FilmViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment(), FilmListAdapter.Listener {

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
        binding.rvFilmList.layoutManager = LinearLayoutManager(requireContext())
        adapter = FilmListAdapter(this)
        binding.rvFilmList.adapter = adapter
        filmVM.filmList.observe(activity as LifecycleOwner, {
            it?.let { adapter.refresh(it) }
        })
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

    override fun onItemClick(item: FilmListItemDetail) {
        val intent = Intent(context, FilmDetailActivity::class.java)
        intent.putExtra("FILM_ID", item.imdbID)
        startActivity(intent)

//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.result_fragment, OneFilmFragment())
//            .addToBackStack(null)
//            .commit()
    }
}