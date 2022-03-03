package ir.es.mohammad.netflix.viewmodel

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.es.mohammad.netflix.model.Movie
import ir.es.mohammad.netflix.network.NetworkManager
import okhttp3.MediaType
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    val registered: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    val fullName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val email: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val username: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val phoneNumber: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val favoriteMovies = HashSet<Movie>()

    fun addMovieToFavorite(movie: Movie) {
        movie.isFavorite = true
        favoriteMovies.add(movie)
    }

    fun removeMovieFromFavorite(movie: Movie) {
        movie.isFavorite = false
        favoriteMovies.remove(movie)
    }
}