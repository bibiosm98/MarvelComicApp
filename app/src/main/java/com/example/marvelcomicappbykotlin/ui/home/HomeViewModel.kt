package com.example.marvelcomicappbykotlin.ui.home

import com.example.marvelcomicappbykotlin.network.MarvelApiStatus
import com.example.marvelcomicappbykotlin.ui.MainViewModel

class HomeViewModel : MainViewModel() {
    init {
        setStatus(MarvelApiStatus.LOADING)
        getMarvelAppComics(null, null)
    }
}