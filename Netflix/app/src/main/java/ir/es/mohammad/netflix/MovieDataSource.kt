package ir.es.mohammad.netflix

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import ir.es.mohammad.netflix.model.BunchOfMovie
import ir.es.mohammad.netflix.model.ComingSoon
import ir.es.mohammad.netflix.model.SearchedMovie
import ir.es.mohammad.netflix.model.TopMovies
import ir.es.mohammad.netflix.network.AppMoviesService
import ir.es.mohammad.netflix.network.ImdbService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource(private val imdbService: ImdbService, private val appMoviesService: AppMoviesService) : IMovieDataSource {
    override fun getComingSoonMovie(lifecycleOwner: LifecycleOwner, callback: AppCallback<ComingSoon>) {
        imdbService.getComingSoonMovies().observe(lifecycleOwner) { res ->
            if (res.isSuccess)
                res.resource?.let { callback.onResponse(it) }
            else
                res.error?.let { callback.onFailure(it) }
        }
    }

    override fun getTopMovies(callback: AppCallback<TopMovies>) {
        imdbService.getTopMovies().enqueue(object : Callback<TopMovies?> {
            override fun onResponse(call: Call<TopMovies?>, response: Response<TopMovies?>) {
                response.body()?.let { callback.onResponse(it) }
            }

            override fun onFailure(call: Call<TopMovies?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    override fun searchForMovie(movieTitle: String, callback: AppCallback<SearchedMovie>) {
        imdbService.searchForMovies(movieTitle).enqueue(object : Callback<SearchedMovie?> {
            override fun onResponse(call: Call<SearchedMovie?>, response: Response<SearchedMovie?>) {
                response.body()?.let { callback.onResponse(it) }
            }

            override fun onFailure(call: Call<SearchedMovie?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    override fun getBunchOfMovie(pageNumber: Int, callback: AppCallback<BunchOfMovie>) {
        appMoviesService.searchForMovies(pageNumber.toString()).enqueue(object : Callback<BunchOfMovie?> {
            override fun onResponse(call: Call<BunchOfMovie?>, response: Response<BunchOfMovie?>) {
                response.body()?.let { callback.onResponse(it) }
            }

            override fun onFailure(call: Call<BunchOfMovie?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}