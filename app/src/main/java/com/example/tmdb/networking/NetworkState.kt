package com.example.tmdb.networking

/**
 * Different states for any network request.
 */
sealed class NetworkState {


    data class Success(
        val success: Boolean = true
    ) : NetworkState()


    data class Loading(
        val loading: Boolean = false
    ) : NetworkState()


    data class Error(
        val errorMessage: String = ""
    ) : NetworkState()



    /**
     * Check if current network state is [Success].
     *
     * @return True if is success state, otherwise false.
     */
    fun isSuccess() = this is Success

    /**
     * Check if current network state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current network state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error
}
