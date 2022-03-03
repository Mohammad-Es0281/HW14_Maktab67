package ir.es.mohammad.netflix.model

data class BunchOfMovie(
    val messageStatus: String,
    val results: List<ResultX>,
    val status: Int,
    val success: Boolean,
    val total_pages: Int,
    val total_results: Int
)