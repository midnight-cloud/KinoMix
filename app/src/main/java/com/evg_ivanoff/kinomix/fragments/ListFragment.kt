package com.evg_ivanoff.kinomix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.evg_ivanoff.kinomix.FilmListAdapter
import com.evg_ivanoff.kinomix.FilmListItem
import com.evg_ivanoff.kinomix.R
import com.evg_ivanoff.kinomix.databinding.FragmentListBinding
import com.evg_ivanoff.kinomix.databinding.FragmentSearchBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var listItem: MutableList<FilmListItem>
    private lateinit var adapter: FilmListAdapter

    private lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            listItem = it.getParcelable("FILM_LIST")!!
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
        if (listItem != null) {
            binding.rvFilmList.layoutManager = LinearLayoutManager(requireContext())
            adapter = FilmListAdapter()
            adapter.refresh(listItem)
        }
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