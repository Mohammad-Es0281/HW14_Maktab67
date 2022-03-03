package ir.es.mohammad.netflix

import ir.es.mohammad.netflix.network.NetworkManager

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