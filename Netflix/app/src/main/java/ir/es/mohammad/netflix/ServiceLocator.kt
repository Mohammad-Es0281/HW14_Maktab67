package ir.es.mohammad.netflix

import ir.es.mohammad.netflix.remote.MovieDataSource
import ir.es.mohammad.netflix.network.NetworkManager
import ir.es.mohammad.netflix.repository.MovieRepository

class ServiceLocator {

    enum class DataSourceType() {
        NETWORK
    }

    private val networkDataSource = MovieDataSource(NetworkManager.imdbService, NetworkManager.appMoviesService)

    fun getRepository(dataSourceType: DataSourceType): MovieRepository {
        return when (dataSourceType) {
            DataSourceType.NETWORK -> MovieRepository(networkDataSource)
        }
    }
}