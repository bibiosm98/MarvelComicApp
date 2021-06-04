package com.example.marvelcomicappbykotlin.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.marvelcomicappbykotlin.databinding.FragmentHomeBinding
import com.example.marvelcomicappbykotlin.network.Comic
import com.example.marvelcomicappbykotlin.ui.ComicAdapter
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment(), OnComicItemLongClick {
    private lateinit var binding: FragmentHomeBinding

    private val adapter = ComicAdapter(this)

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(inflater)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.comicRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.comicList.observe(viewLifecycleOwner) { list ->
            adapter.setComicList(list)
        }
    }

    override fun onComicItemLongClick(comic: Comic, position: Int) {
        Log.d("OnClick", comic.toString())
        comic.title.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }
}