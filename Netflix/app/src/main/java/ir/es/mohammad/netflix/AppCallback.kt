package ir.es.mohammad.netflix

interface AppCallback<T> {
    fun onResponse(response: T);

    fun onFailure(error: Throwable);
}