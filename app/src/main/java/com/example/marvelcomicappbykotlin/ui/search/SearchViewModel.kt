package com.example.marvelcomicappbykotlin.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelcomicappbykotlin.ui.MainViewModel

class SearchViewModel : MainViewModel() {

    fun onTitleSelected(title: String){
        getMarvelAppComics(title, 20)
        Log.i("TITLE", title)
    }
}