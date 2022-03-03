package ir.es.mohammad.netflix.model

data class SearchedMovie(
    val errorMessage: String,
    val expression: String,
    val results: List<Result>,
    val searchType: String
)