package com.evg_ivanoff.kinomix.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.FilmListItem
import com.evg_ivanoff.kinomix.R
import com.evg_ivanoff.kinomix.databinding.FragmentSearchBinding
import com.evg_ivanoff.kinomix.retrofit.Common
import com.evg_ivanoff.kinomix.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentSearchBinding
    private lateinit var mService: RetrofitServices

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
        initSpinner()
        binding.btnSearch.setOnClickListener {
            mService = Common.retrofitServices
            mService.getTest(
                Common.API_KEY,
                "tt1285016"
            )
                .enqueue(object : Callback<Film> {

                    override fun onResponse(call: Call<Film>, response: Response<Film>) {
                        val fragment = OneFilmFragment().apply {
                            arguments = Bundle().apply {
                                putString("FILM_TITLE", response.body()?.title)
                            }
                        }
                        Toast.makeText(context, "Title: ${response.body()?.title}", Toast.LENGTH_SHORT).show()
                        launchFragment(fragment)
                    }

                    override fun onFailure(call: Call<Film>, t: Throwable) {
                        Toast.makeText(context, "404 error", Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }

    private fun launchFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.result_fragment, fragment)
            .commit()
    }

    private fun initSpinner() {
        val searches = resources.getStringArray(R.array.serach)
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            searches
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinChooseSearch.adapter = adapter
//        binding.spinChooseSearch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//
//            }
//        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}