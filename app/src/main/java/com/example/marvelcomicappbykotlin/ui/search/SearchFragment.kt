package com.example.marvelcomicappbykotlin.ui.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
//import android.widget.SearchView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.marvelcomicappbykotlin.MainActivity
import com.example.marvelcomicappbykotlin.R
import com.example.marvelcomicappbykotlin.databinding.FragmentSearchBinding
import com.example.marvelcomicappbykotlin.network.Comic
import com.example.marvelcomicappbykotlin.network.MarvelApiStatus
import com.example.marvelcomicappbykotlin.ui.ComicAdapter
import com.example.marvelcomicappbykotlin.ui.OnComicItemLongClick
import com.example.marvelcomicappbykotlin.ui.home.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.snackbar.Snackbar

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
                    binding.searchInfo.setText(R.string.search_start)
                }
                MarvelApiStatus.LOADING -> {
                    binding.searchInfo.visibility = View.GONE
                }
                MarvelApiStatus.ERROR -> {
                    binding.searchInfo.visibility = View.VISIBLE
                    binding.searchInfo.text = resources.getString(R.string.search_error, title)
                }
                MarvelApiStatus.DONE -> {
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
        {
//        menu.clear()
//        inflater.inflate(R.menu.search_menu, menu)
//        searchView = SearchView(((context as MainActivity).supportActionBar?.themedContext ?: context)!!)
//        menu.findItem(R.id.search).apply {
//            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
//            actionView = searchView
//        }
//
//        searchView.queryHint = resources.getString(R.string.search_hint)
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                Log.i("TEXT SUB", query)
//                searchView.clearFocus()
////                searchView.setQuery("googd job!", false)
//                searchViewModel.onTitleSelected(query)
//                Log.i("SIZE ", searchViewModel.comicList.value?.size.toString())
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                Log.i("TEXT", newText)
//                return false
//            }
//        })
//        searchView.setOnClickListener {view ->
//            view.findFocus()
//        }
        }
/////////////
        searchView = binding.mySearchView
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("TEXT SUB", query ?: "")
                searchView.clearFocus()
                searchViewModel.onTitleSelected(query ?: "")
                title = query.toString()
                Log.i("SIZE ", searchViewModel.comicList.value?.size.toString())
                binding.cancelBtn.visibility = View.GONE
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("TEXT", newText ?: "")
                return false
            }
        })

        searchView.setOnClickListener{
            binding.cancelBtn.visibility = View.VISIBLE
            Snackbar.make(requireView(), "setOnClickListener", Snackbar.LENGTH_SHORT).show()
            searchView.setIconifiedByDefault(false)
        }
        searchView.setOnSearchClickListener {
            binding.cancelBtn.visibility = View.VISIBLE
            Snackbar.make(requireView(), "setOnSearchClickListener", Snackbar.LENGTH_SHORT).show()
            searchView.setIconifiedByDefault(false)
        }
        searchView.setOnCloseListener {
            binding.cancelBtn.visibility = View.GONE
            return@setOnCloseListener false
        }
        searchView.setOnContextClickListener{
            Snackbar.make(requireView(), "setOnContextClickListener", Snackbar.LENGTH_SHORT).show()
            return@setOnContextClickListener false
        }
        searchView.setOnFocusChangeListener{view, hasFocus ->
            binding.cancelBtn.visibility = View.VISIBLE

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