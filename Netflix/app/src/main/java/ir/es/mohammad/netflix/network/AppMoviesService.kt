package ir.es.mohammad.netflix.network

import ir.es.mohammad.netflix.model.BunchOfMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AppMoviesService {

    @GET("/api/movies?limit=24&sort=rating")
    fun searchForMovies(@Query("page") pageNumber: String): Call<BunchOfMovie>
}