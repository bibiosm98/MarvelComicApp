package com.example.marvelcomicappbykotlin.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelcomicappbykotlin.network.Comic
import com.example.marvelcomicappbykotlin.network.MarvelApi
import com.example.marvelcomicappbykotlin.ui.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MarvelApiStatus { LOADING, ERROR, DONE}

class HomeViewModel : MainViewModel() {
    init {
        getMarvelAppComics(null, null)
    }

    fun displayComicDetails(comic: Comic) {
//        _navigateToSelectedComic.value = comic
    }
//
//    fun getPureList(): List<Comic> {
//        return pureComicList
//    }
}