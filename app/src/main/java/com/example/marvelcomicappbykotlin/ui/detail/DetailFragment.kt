package com.example.marvelcomicappbykotlin.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marvelcomicappbykotlin.databinding.DetailFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.detail_fragment.*


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        binding = DetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val selectedComic = DetailFragmentArgs.fromBundle(requireArguments()).selectedComic
        val viewModelFactory = DetailViewModelFactory(selectedComic, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        BottomSheetBehavior.from(binding.bottomSheet).apply {
            peekHeight = 400
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.findOutMoreBtn.setOnClickListener{
//            val webpage: Uri = Uri.parse(viewModel.selectedComic.value.resourceURI)
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(viewModel.selectedComic.value?.urls?.get(0)?.url)
            })
        }
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
    }
}