package com.example.marvelcomicappbykotlin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.marvelcomicappbykotlin.R
import com.example.marvelcomicappbykotlin.network.Comic

interface OnComicItemLongClick{
    fun onComicItemLongClick(comic: Comic, position: Int)
}

@Suppress("DEPRECATION")
class ComicAdapter(private val listener: OnComicItemLongClick): RecyclerView.Adapter<ComicAdapter.ComicViewHolder>(){
    private val comicList = ArrayList<Comic>()

    fun setComicList(list: List<Comic>){
        comicList.clear()
        comicList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return comicList.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicAdapter.ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.comic_item, parent, false)
        return ComicViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicAdapter.ComicViewHolder, position: Int) {

        val title = holder.itemView.findViewById<TextView>(R.id.comicTitle)
        val authors = holder.itemView.findViewById<TextView>(R.id.comicAuthors)
        val description = holder.itemView.findViewById<TextView>(R.id.comicDescription)
        val image = holder.itemView.findViewById<ImageView>(R.id.comic_item_imageView)

        var authorsList = ""
        val items = comicList[holder.adapterPosition].creators.items
        if(!items.isEmpty()){
            authorsList = "written by "
            items.forEach{ auth ->
                authorsList += auth.name + ", "
            }
        }
        title.text = comicList[holder.adapterPosition].title
        authors.text = authorsList
        description.text = comicList[holder.adapterPosition].description.toString()

        val imgUri = comicList[holder.absoluteAdapterPosition].thumbnail.path.toUri().buildUpon().scheme("https").appendPath("/portrait_uncanny.jpg").build()
        Glide.with(holder.itemView)
            .load(imgUri)
            .apply(RequestOptions().transform(RoundedCorners(16)))
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.marvel_placeholder)
//                    .error(R.drawable.ic_round_search_24)
            )
            .into(image)
    }

    inner class ComicViewHolder(view: View): RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener(){
                listener.onComicItemLongClick(comicList[adapterPosition], adapterPosition)
                true
            }
        }
    }
}