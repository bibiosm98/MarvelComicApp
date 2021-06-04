package com.example.marvelcomicappbykotlin

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.marvelcomicappbykotlin.network.Comic
import com.example.marvelcomicappbykotlin.ui.home.HomeListAdapter

//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<Comic>?){
//    val adapter = recyclerView.adapter as HomeListAdapter
//    adapter.submitList(data)
//}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))

    imgUrl?.let{
        val imgUri = it.toUri().buildUpon().scheme("https").appendPath("/portrait_uncanny.jpg").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(requestOptions)
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_round_home_24)
            )
            .into(imgView)
    }
}