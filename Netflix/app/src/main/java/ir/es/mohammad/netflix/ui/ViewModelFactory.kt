package ir.es.mohammad.netflix.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.es.mohammad.netflix.ServiceLocator
import ir.es.mohammad.netflix.ui.comingsoon.ComingSoonViewModel
import ir.es.mohammad.netflix.ui.home.HomeMovieViewModel
import ir.es.mohammad.netflix.ui.search.SearchMovieViewModel

class ViewModelFactory(private val lifecycleOwner: LifecycleOwner? = null): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ComingSoonViewModel::class.java) -> ComingSoonViewModel(lifecycleOwner!!, ServiceLocator().getRepository(
                ServiceLocator.DataSourceType.NETWORK
            )) as T
            modelClass.isAssignableFrom(HomeMovieViewModel::class.java) -> HomeMovieViewModel(
                ServiceLocator().getRepository(ServiceLocator.DataSourceType.NETWORK)) as T
            modelClass.isAssignableFrom(SearchMovieViewModel::class.java) -> SearchMovieViewModel(
                ServiceLocator().getRepository(ServiceLocator.DataSourceType.NETWORK)) as T
            else -> modelClass.newInstance()
        }
    }
}