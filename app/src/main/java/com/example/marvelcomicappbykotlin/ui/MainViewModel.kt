package com.example.marvelcomicappbykotlin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelcomicappbykotlin.network.Comic
import com.example.marvelcomicappbykotlin.network.MarvelApi
import com.example.marvelcomicappbykotlin.ui.home.MarvelApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class MainViewModel: ViewModel() {
    private val _status = MutableLiveData<MarvelApiStatus>()
    val status: LiveData<MarvelApiStatus>
        get() = _status

    private val _comicList = MutableLiveData<List<Comic>>()
    val comicList: LiveData<List<Comic>>
        get() = _comicList
//
//    val pureComicList: List<Comic>
//        get() = _comicList.value!!

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getMarvelAppComics(title: String?, limit: Int?) {
        coroutineScope.launch {
            val getPropertyDeferred = MarvelApi.retrofitService.getComics(
                "1",
                "080a502746c8a60aeab043387a56eef0",
                "6edc18ab1a954d230c1f03c590d469d2",
                title,
                limit
            ) // default limit="20"
            try {
                _status.value = MarvelApiStatus.LOADING
                val listResult = getPropertyDeferred.await()
                if (listResult.data.total > 0) {
                    _status.value = MarvelApiStatus.DONE
                    _comicList.value = listResult.data.results
                    Log.i("LIST SIZE", _comicList.value!!.size.toString())
                    Log.i("LIST", _comicList.value.toString())
                }
            } catch (t: Throwable) {
                _status.value = MarvelApiStatus.ERROR
                _comicList.value = ArrayList()
            }
        }
    }

    fun clearComicList(){
        _comicList.value = null;
    }
}