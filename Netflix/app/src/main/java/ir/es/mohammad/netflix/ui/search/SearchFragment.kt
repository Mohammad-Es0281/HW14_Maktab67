package ir.es.mohammad.netflix.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import ir.es.mohammad.netflix.R
import ir.es.mohammad.netflix.ui.ViewModelFactory
import ir.es.mohammad.netflix.adapter.MovieRecyclerAdapter
import ir.es.mohammad.netflix.databinding.FragmentSearchBinding


class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchMovieViewModel by viewModels { ViewModelFactory(this) }
    private val mAdapter by lazy { MovieRecyclerAdapter(ArrayList()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        setRecyclerView()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.searchMovie(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchViewModel.movies.observe(this) {
            Log.d("TAGG", "Observe")
            mAdapter.setData(ArrayList(it)) }
    }

    private fun setRecyclerView() {
        binding.searchRecycleView.layoutManager = GridLayoutManager(requireContext(), 3)
        mAdapter.setEachItem = { movie ->
            setMovie(movie)
            viewHolderBinding.btnMovieAction.visibility = View.GONE
        }
        binding.searchRecycleView.adapter = mAdapter
    }
}