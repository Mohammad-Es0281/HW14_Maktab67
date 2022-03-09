package ir.es.mohammad.netflix.repository

import androidx.lifecycle.LifecycleOwner
import ir.es.mohammad.netflix.AppCallback
import ir.es.mohammad.netflix.model.BunchOfMovie
import ir.es.mohammad.netflix.model.ComingSoon
import ir.es.mohammad.netflix.model.SearchedMovie
import ir.es.mohammad.netflix.model.TopMovies
import ir.es.mohammad.netflix.remote.IMovieDataSource

class MovieRepository(private val remoteDataSource: IMovieDataSource) {

    fun getComingSoonMovies(lifecycleOwner: LifecycleOwner, callback: AppCallback<ComingSoon>) {
        remoteDataSource.getComingSoonMovie(lifecycleOwner, callback)
    }

    fun getTopMovies(callback: AppCallback<TopMovies>) {
        remoteDataSource.getTopMovies(callback)
    }

    fun searchForMovie(movieTitle: String, callback: AppCallback<SearchedMovie>) {
        remoteDataSource.searchForMovie(movieTitle, callback);
    }

    fun getBunchOfMovie(pageNumber: Int, callback: AppCallback<BunchOfMovie>){
        remoteDataSource.getBunchOfMovie(pageNumber, callback)
    }
}