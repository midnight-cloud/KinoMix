package com.evg_ivanoff.kinomix.models

import androidx.lifecycle.*
import com.evg_ivanoff.kinomix.Film
import com.evg_ivanoff.kinomix.room.FavoriteFilmsRepository
import kotlinx.coroutines.launch

//эту модель я использую для общения с репозиторием, который общается с БД
class FavoriteFilmsViewModel(private val repository: FavoriteFilmsRepository): ViewModel() {
    val allFavoriteFilms: LiveData<List<Film>> = repository.allFavoriteFilms.asLiveData()
    fun insert(film: Film) = viewModelScope.launch {
        repository.insert(film)
    }
    fun delete(film: Film) = viewModelScope.launch {
        repository.delete(film)
    }
}

class FavoriteFilmsViewModelFactory(private val repository: FavoriteFilmsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteFilmsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteFilmsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}