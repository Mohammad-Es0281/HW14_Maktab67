package ir.es.mohammad.netflix

import androidx.lifecycle.LifecycleOwner
import ir.es.mohammad.netflix.model.*

interface IMovieDataSource{
    fun getComingSoonMovie(lifecycleOwner: LifecycleOwner, callback: AppCallback<ComingSoon>);

    fun getTopMovies(callback: AppCallback<TopMovies>)

    fun searchForMovie(movieTitle: String, callback: AppCallback<SearchedMovie>)

    fun getBunchOfMovie(pageNumber: Int, callback: AppCallback<BunchOfMovie>)
}