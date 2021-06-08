package com.example.marvelcomicappbykotlin.ui.search

import com.example.marvelcomicappbykotlin.network.MarvelApiStatus
import com.example.marvelcomicappbykotlin.ui.MainViewModel

class SearchViewModel : MainViewModel() {

    fun onTitleSelected(title: String){
        setStatus(MarvelApiStatus.WAITING)
        getMarvelAppComics(title, 20)
    }
}