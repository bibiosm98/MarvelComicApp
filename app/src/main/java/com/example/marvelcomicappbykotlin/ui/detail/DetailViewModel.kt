package com.example.marvelcomicappbykotlin.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvelcomicappbykotlin.network.Comic

class DetailViewModel(comic: Comic, app: Application) : AndroidViewModel(app) {

    private val _selectedComic = MutableLiveData<Comic>()
    val selectedComic: LiveData<Comic>
        get() = _selectedComic

    init {
        _selectedComic.value = comic
    }

    fun getAuthors(): String{
        var authorsList = ""
        val items = _selectedComic.value?.creators?.items
        if(!items?.isEmpty()!!){
            authorsList = "written by "
            items.forEach{ auth ->
                authorsList += auth.name + ", "
            }
            authorsList = authorsList.substring(0, authorsList.length - 2);
        }

        return authorsList
    }
}