package ir.es.mohammad.netflix.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import ir.es.mohammad.netflix.*
import ir.es.mohammad.netflix.adapter.MovieRecyclerAdapter
import ir.es.mohammad.netflix.databinding.FragmentHomeBinding
import ir.es.mohammad.netflix.model.Movie
import ir.es.mohammad.netflix.ui.UserViewModel
import ir.es.mohammad.netflix.ui.ViewModelFactory

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val userViewModel: UserViewModel by activityViewModels()
    private val homeMovieViewModel: HomeMovieViewModel by viewModels{ ViewModelFactory() }
    private val mAdapter by lazy { MovieRecyclerAdapter(ArrayList()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        setRecyclerView()
        homeMovieViewModel.newAddedMovies.observe(this){
            Log.d("TAGG", "I observe")
            binding.progressBar.visibility = View.GONE
            mAdapter.addData(ArrayList(it))
        }

        binding.recyclerViewHomeMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (homeMovieViewModel.moreMovieExist && !recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.progressBar.visibility = View.VISIBLE
                    homeMovieViewModel.fillMovies()
                }
            }
        })
    }

    private fun setRecyclerView(){
        binding.recyclerViewHomeMovies.layoutManager = GridLayoutManager(requireContext(), 3)
        mAdapter.setEachItem = { movie -> viewHolderBinding.btnMovieAction.setMovieActionButton(movie) }
        binding.recyclerViewHomeMovies.adapter = mAdapter
    }

    private fun ImageButton.setMovieActionButton(movie: Movie) {
        if (movie.isFavorite)
            setImageResource(R.drawable.ic_baseline_favorite_24)
        else
            setImageResource(R.drawable.ic_outline_favorite)

        setOnClickListener {
            if (userViewModel.registered.value!!) {
                if (movie.isFavorite) {
                    userViewModel.removeMovieFromFavorite(movie)
                    setImageResource(R.drawable.ic_outline_favorite)
                } else {
                    userViewModel.addMovieToFavorite(movie)
                    setImageResource(R.drawable.ic_baseline_favorite_24)
                }
            } else
                showRegisterSnackbar()
        }
    }

    private fun showRegisterSnackbar() {
        Snackbar.make(binding.root, "First you must register", Snackbar.LENGTH_LONG).apply {
            setAction("Register") {
                val bottomNavigationView =
                    requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
                bottomNavigationView.selectedItemId = R.id.showInfoFragment
            }
        }.show()
    }
}