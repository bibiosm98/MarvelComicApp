package com.example.marvelcomicappbykotlin.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelcomicappbykotlin.network.Comic
import com.example.marvelcomicappbykotlin.network.ComicData
import com.example.marvelcomicappbykotlin.network.ComicProperty
import com.example.marvelcomicappbykotlin.network.MarvelApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class HomeViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _comicList = MutableLiveData<List<Comic>>()
    val comicList: LiveData<List<Comic>>
        get() = _comicList


    private val _comic = MutableLiveData<Comic>()
    val comic: LiveData<Comic>
        get() = _comic


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    init {
        getMarvelAppComics()
    }
    private fun getMarvelAppComics(){
        coroutineScope.launch {
            var getPropertyDeferred = MarvelApi.retrofitService.getComics()
            try{
                var listResult = getPropertyDeferred.await()
                _status.value = "WITAMY"
                if(listResult.data.total > 0){
                    _comic.value = listResult.data.results[4]
                    _comic.value!!.thumbnail.path =  _comic.value!!.thumbnail.path + "/portrait_xlarge.jpg"
                }
            }catch (t: Throwable) {
                _status.value = "Failure: " + t.message
            }
        }

    }
//    private fun getMarvelAppComics(){
//        MarvelApi.retrofitService.getComics().enqueue( object: Callback<ComicProperty> {
//            override fun onFailure(call: Call<ComicProperty>, t: Throwable) {
//                _response.value = "Failure: " + t.message
//            }
//
//            override fun onResponse(call: Call<ComicProperty>, response: Response<ComicProperty>) {
//                _response.value = response.body()?.data?.results?.get(3)?.description!!
//                _comicList.value = response.body()!!.data.results
////                Log.i("response", response.body()?.data?.results?.get(0)?.description!!)
////                Log.i("response Avaliable?", "HALOO")
//            }
//        })
//        _response.value = "Waiting"
//    }
//
}