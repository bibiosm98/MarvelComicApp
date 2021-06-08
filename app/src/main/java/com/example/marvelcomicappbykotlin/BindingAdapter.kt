package com.example.marvelcomicappbykotlin

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.marvelcomicappbykotlin.network.MarvelApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transforms(CenterCrop())//, RoundedCorners(16))

    imgUrl?.let{
        val imgUri = it.toUri().buildUpon().scheme("https").appendPath("/landscape_xlarge.jpg").build()
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

@BindingAdapter("marvelApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarvelApiStatus?) {
    when (status) {
        MarvelApiStatus.WAITING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarvelApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarvelApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_image)
        }
        MarvelApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {

        }
    }
}

//@BindingAdapter("marvelApiStatusText")
//fun bindStatus(statusTextView: TextView, status: MarvelApiStatus?) {
//    when (status) {
//        MarvelApiStatus.WAITING -> {
//            statusTextView.visibility = View.VISIBLE
//            statusTextView.setText(R.string.search_start)
//        }
//        MarvelApiStatus.LOADING -> {
//            statusTextView.visibility = View.VISIBLE
//            statusTextView.setImageResource(R.drawable.loading_animation)
//        }
//        MarvelApiStatus.ERROR -> {
//            statusTextView.visibility = View.VISIBLE
//            statusTextView.setImageResource(R.drawable.book_open_icon)
//        }
//        MarvelApiStatus.DONE -> {
//            statusTextView.visibility = View.GONE
//        }
//        else -> {
//
//        }
//    }
//}