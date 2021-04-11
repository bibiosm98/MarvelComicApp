package com.example.marvelcomicappbykotlin.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.marvelcomicappbykotlin.databinding.FragmentHomeBinding
import com.example.marvelcomicappbykotlin.databinding.GridViewItemBinding

class HomeFragment : Fragment() {

//    private lateinit var homeViewModel: HomeViewModel

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

//    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = GridViewItemBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setHasOptionsMenu(true)
//        viewModel.response.observe(viewLifecycleOwner, Observer {
//            Log.i("response", it)
//            binding.response.text = it.toString()
//        })


        return binding.root
    }
}