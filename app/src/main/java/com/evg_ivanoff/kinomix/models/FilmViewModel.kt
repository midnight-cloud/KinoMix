package com.evg_ivanoff.kinomix.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.FilmListItemDetail

open class FilmViewModel: ViewModel() {
    var filmList: MutableLiveData<List<FilmListItemDetail>> = MutableLiveData()

    fun getSearchFilmList() = filmList

    fun setSearchFilmList(update: List<FilmListItemDetail>) {
        this.filmList = MutableLiveData(update)
    }

    var filmDetail : MutableLiveData<Film> = MutableLiveData()

    fun setFilmDetail(film: Film) {
        this.filmDetail = MutableLiveData(film)
    }

    var favoriteFilms : MutableLiveData<List<Film>> = MutableLiveData()
    fun setFavoriteFilms(film: Film) {
        this.favoriteFilms = MutableLiveData(listOf(film))
    }
    fun deleteFavoriteFilms() {
        favoriteFilms = MutableLiveData(listOf())
    }
}