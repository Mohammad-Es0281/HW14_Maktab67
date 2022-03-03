package ir.es.mohammad.netflix.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import ir.es.mohammad.netflix.R
import ir.es.mohammad.netflix.ViewModelFactory
import ir.es.mohammad.netflix.adapter.MovieRecyclerAdapter
import ir.es.mohammad.netflix.databinding.FragmentComingSoonBinding
import ir.es.mohammad.netflix.databinding.FragmentSearchBinding
import ir.es.mohammad.netflix.model.Movie
import ir.es.mohammad.netflix.viewmodel.ComingSoonViewModel
import ir.es.mohammad.netflix.viewmodel.SearchMovieViewModel
import ir.es.mohammad.netflix.viewmodel.UserViewModel


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
                Log.d("TAGG", "I am Clicked")
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