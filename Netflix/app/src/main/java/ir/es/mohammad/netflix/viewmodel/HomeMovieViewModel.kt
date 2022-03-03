package ir.es.mohammad.netflix.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.es.mohammad.netflix.AppCallback
import ir.es.mohammad.netflix.MovieRepository
import ir.es.mohammad.netflix.model.BunchOfMovie
import ir.es.mohammad.netflix.model.Movie

class HomeMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val newAddedMovies: MutableLiveData<MutableSet<Movie>> by lazy { MutableLiveData<MutableSet<Movie>>() }

    private var pageNumber = 0
    private var maxPageNumber = -1
    val moreMovieExist = !(maxPageNumber == 0 || pageNumber == maxPageNumber)

    init {
        fillMovies()
    }

    fun fillMovies() {
        if (moreMovieExist) {
            Log.d("TAGG", "Go for request")
            movieRepository.getBunchOfMovie(++pageNumber, object : AppCallback<BunchOfMovie> {
                override fun onResponse(response: BunchOfMovie) {
                    Log.d("TAGG", newAddedMovies.value?.size.toString())
                    maxPageNumber = response.total_pages
                    val set = mutableSetOf<Movie>()
                    response.results.forEach { set.add(Movie(it.title, it.image)) }
                    newAddedMovies.postValue(set)
                }

                override fun onFailure(error: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

}