package com.example.marvelcomicappbykotlin.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marvelcomicappbykotlin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

//    private lateinit var homeViewModel: HomeViewModel

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_home, container, false)
////        val textView: TextView = root.findViewById(R.id.text_home)
////        homeViewModel.text.observe(viewLifecycleOwner, Observer {
////            textView.text = it
////        })
//        return root

//        val binding = FragmentHomeBinding.inflate(inflater)
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.lifecycleOwner = this
//        binding.viewModel = viewModel

        setHasOptionsMenu(true)
        return binding.root
    }
}