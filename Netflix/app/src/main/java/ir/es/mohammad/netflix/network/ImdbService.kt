package ir.es.mohammad.netflix.network

import androidx.lifecycle.LiveData
import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import com.github.leonardoxh.livedatacalladapter.Resource
import ir.es.mohammad.netflix.model.ComingSoon
import ir.es.mohammad.netflix.model.SearchedMovie
import ir.es.mohammad.netflix.model.TopMovies
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ImdbService {
    @GET("/{lang}/API/ComingSoon/k_d6pbw34n")
    fun getComingSoonMovies(@Path("lang") language: String = "en"): LiveData<Resource<ComingSoon>>

    @GET("/{lang}/API/Top250Movies/k_d6pbw34n")
    fun getTopMovies(@Path("lang") language: String = "en"): Call<TopMovies>

    @GET("/{lang}/API/SearchMovie/k_d6pbw34n/{expression}")
    fun searchForMovies(
        @Path("expression") expression: String,
        @Path("lang") language: String = "en"
    ): Call<SearchedMovie>
}