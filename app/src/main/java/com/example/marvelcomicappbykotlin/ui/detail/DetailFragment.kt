package com.example.marvelcomicappbykotlin.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelcomicappbykotlin.R
import com.example.marvelcomicappbykotlin.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val selectedComic = DetailFragmentArgs.fromBundle(requireArguments()).selectedComic
        val viewModelFactory = DetailViewModelFactory(selectedComic, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailViewModel::class.java)

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}