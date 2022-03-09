package ir.es.mohammad.netflix.ui.comingsoon

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.es.mohammad.netflix.AppCallback
import ir.es.mohammad.netflix.repository.MovieRepository
import ir.es.mohammad.netflix.model.ComingSoon
import ir.es.mohammad.netflix.model.Movie

class ComingSoonViewModel(private val lifecycleOwner: LifecycleOwner, private val movieRepository: MovieRepository) : ViewModel() {
    val comingSoonMovies: MutableLiveData<HashSet<Movie>> by lazy { MutableLiveData<HashSet<Movie>>() }

    init { fillComingSoonMovie() }

    private fun fillComingSoonMovie() {
        movieRepository.getComingSoonMovies(lifecycleOwner, object : AppCallback<ComingSoon> {
            override fun onResponse(response: ComingSoon) {
                val hashSet = HashSet<Movie>()
                response.items.forEach { hashSet.add(Movie(it.title, it.image)) }
                comingSoonMovies.postValue(hashSet)
            }

            override fun onFailure(error: Throwable) {
                TODO("Not  yet implemented")
            }
        })
    }
}