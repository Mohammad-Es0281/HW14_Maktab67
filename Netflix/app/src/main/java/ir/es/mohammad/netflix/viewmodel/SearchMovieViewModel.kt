package ir.es.mohammad.netflix.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.es.mohammad.netflix.AppCallback
import ir.es.mohammad.netflix.MovieRepository
import ir.es.mohammad.netflix.model.Movie
import ir.es.mohammad.netflix.model.SearchedMovie
import java.util.HashSet

class SearchMovieViewModel(private val movieRepository: MovieRepository): ViewModel() {
    val movies: MutableLiveData<MutableSet<Movie>> by lazy { MutableLiveData<MutableSet<Movie>>() }


    fun searchMovie(movieTitle: String) {
        movieRepository.searchForMovie(movieTitle, object : AppCallback<SearchedMovie> {
            override fun onResponse(response: SearchedMovie) {
                val hashSet = HashSet<Movie>()
                response.results.forEach { hashSet.add(Movie(it.title, it.image)) }
                movies.postValue(hashSet)
            }

            override fun onFailure(error: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}