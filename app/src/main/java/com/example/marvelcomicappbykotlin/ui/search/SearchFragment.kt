package com.example.marvelcomicappbykotlin.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.marvelcomicappbykotlin.R
import com.example.marvelcomicappbykotlin.databinding.FragmentSearchBinding
import com.example.marvelcomicappbykotlin.network.Comic
import com.example.marvelcomicappbykotlin.network.MarvelApiStatus
import com.example.marvelcomicappbykotlin.ui.ComicAdapter
import com.example.marvelcomicappbykotlin.ui.OnComicItemLongClick

class SearchFragment : Fragment(), OnComicItemLongClick {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchView: SearchView
    private val adapter = ComicAdapter(this)
    private var title = ""

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater)

        binding.viewModel = searchViewModel
        binding.lifecycleOwner = this
        binding.comicSearchRecyclerView.adapter = adapter


        searchViewModel.navigateToSelectedComic.observe(viewLifecycleOwner, Observer {
            if(null != it){
                this.findNavController().navigate(SearchFragmentDirections.searchToDetailFragment(it))
                searchViewModel.displayComicDetailsComplete()
            }
        })

        searchViewModel.status.observe(viewLifecycleOwner, Observer {
            when(it){
                MarvelApiStatus.WAITING -> {
                    binding.searchInfo.visibility = View.VISIBLE
                    binding.searchProgressbar.visibility = View.GONE
                    binding.searchInfo.setText(R.string.search_start)
                }
                MarvelApiStatus.LOADING -> {
                    binding.searchInfo.visibility = View.GONE
                    binding.searchProgressbar.visibility = View.VISIBLE
                }
                MarvelApiStatus.ERROR -> {
                    binding.searchInfo.visibility = View.VISIBLE
                    binding.searchProgressbar.visibility = View.GONE
                    binding.searchInfo.text = resources.getString(R.string.search_error, title)
                }
                MarvelApiStatus.DONE -> {
                    binding.searchProgressbar.visibility = View.GONE
                    binding.searchInfo.visibility = View.GONE
                }
                else -> {

                }
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        searchView = binding.mySearchView
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchViewModel.onTitleSelected(query ?: "")
                title = query.toString()
                binding.cancelBtn.visibility = View.GONE
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                binding.cancelBtn.visibility = View.VISIBLE
                return false
            }
        })


        // I know, this is sphagetti to fix, but i looking for better way of implementing this "Cancel" action
        searchView.setOnClickListener{
            binding.cancelBtn.visibility = View.VISIBLE
            searchView.setIconifiedByDefault(false)
        }

        binding.cancelBtn.setOnClickListener{
            searchView.focusable = SearchView.NOT_FOCUSABLE
            searchView.clearFocus()
            binding.cancelBtn.visibility = View.GONE
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchViewModel.comicList.observe(viewLifecycleOwner){ list ->
            if (list != null) {
                adapter.setComicList(list)
            }
        }
    }

    override fun onComicItemLongClick(comic: Comic, position: Int) {
        searchViewModel.displayComicDetails(comic)
    }
}