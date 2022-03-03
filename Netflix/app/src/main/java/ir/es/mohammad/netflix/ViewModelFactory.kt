package ir.es.mohammad.netflix

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.es.mohammad.netflix.fragments.ComingSoonFragment
import ir.es.mohammad.netflix.fragments.HomeFragment
import ir.es.mohammad.netflix.viewmodel.ComingSoonViewModel
import ir.es.mohammad.netflix.viewmodel.HomeMovieViewModel
import ir.es.mohammad.netflix.viewmodel.SearchMovieViewModel

class ViewModelFactory(private val lifecycleOwner: LifecycleOwner? = null): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ComingSoonViewModel::class.java))
            return ComingSoonViewModel(lifecycleOwner!!, ServiceLocator().getRepository(ServiceLocator.DataSourceType.NETWORK)) as T
        else if (modelClass.isAssignableFrom(HomeMovieViewModel::class.java))
            return HomeMovieViewModel(ServiceLocator().getRepository(ServiceLocator.DataSourceType.NETWORK)) as T
        else if (modelClass.isAssignableFrom(SearchMovieViewModel::class.java))
            return SearchMovieViewModel(ServiceLocator().getRepository(ServiceLocator.DataSourceType.NETWORK)) as T
        return modelClass.newInstance()
    }
}