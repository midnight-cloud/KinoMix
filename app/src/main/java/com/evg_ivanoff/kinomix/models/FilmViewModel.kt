package com.evg_ivanoff.kinomix.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.FilmListItemDetail

//эту модель я использую для передачи данных между фрагментами
open class FilmViewModel: ViewModel() {
    var filmList: MutableLiveData<List<FilmListItemDetail>> = MutableLiveData()

    var filmDetail : MutableLiveData<Film> = MutableLiveData()

    var favoriteFilms : MutableLiveData<List<Film>> = MutableLiveData()
}