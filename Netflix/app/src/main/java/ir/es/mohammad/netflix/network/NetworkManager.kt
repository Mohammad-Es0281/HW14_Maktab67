package ir.es.mohammad.netflix.network

import com.github.leonardoxh.livedatacalladapter.LiveDataCallAdapterFactory
import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    val imdbService: ImdbService =
        buildRetrofit(
            "https://imdb-api.com/",
            buildClient("Authorization" to "k_d6pbw34n")
        ).create(ImdbService::class.java)

    val appMoviesService: AppMoviesService =
        buildRetrofit(
            "https://movies-app1.p.rapidapi.com/",
            buildClient("x-rapidapi-host" to "movies-app1.p.rapidapi.com",
                "x-rapidapi-key" to "5272663c23msh7622d1df53777a3p148677jsn6b108209ca06")
        ).create(AppMoviesService::class.java)


    private fun buildClient(vararg headers: Pair<String, String>): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            headers.forEach { requestBuilder.addHeader(it.first, it.second) }
            chain.proceed(requestBuilder.build())
        }
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    private fun buildRetrofit(baseUrl: String, client: OkHttpClient? = null): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
        if (client != null) retrofitBuilder.client(client)
        return retrofitBuilder.build()
    }
}