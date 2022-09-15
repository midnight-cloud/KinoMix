package com.evg_ivanoff.kinomix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evg_ivanoff.kinomix.databinding.FragmentOneFilmBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OneFilmFragment : Fragment(){

    private var param1: String? = null
    private var param2: String? = null
    private var filmTitle: String? = null

    private lateinit var binding: FragmentOneFilmBinding

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
        binding.filmTitle.text = "При большом количестве информации, которую нужно поместить на экране приходится использовать полосы прокрутки. В Android существуют специальные компоненты ScrollView и HorizontalScrollView, которые являются контейнерными элементами и наследуются от ViewGroup. Обратите внимание, что класс TextView использует свою собственную прокрутку и не нуждается в добавлении отдельных полос прокрутки. Но использование отдельных полос даже с TextView может улучшить вид вашего приложения и повышает удобство работы для пользователя."
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OneFilmFragment.
         */
        // TODO: Rename and change types and number of parameters
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