package com.example.test.data.repository

import java.io.IOException

/**
 * Result management for UI and data.
 */
sealed class Result<out T> {

    data class Success<T>(val users: T) : Result<T>()

    data class Error(val exception: Throwable) : Result<Nothing>() {
        val isConnectionError: Boolean get() = exception is IOException
    }

    object Loading : Result<Nothing>()

    companion object {
        fun loading() = Loading

        fun <T> success(data: T) = Success(data)

        fun <T> successList(list: List<T>): Result<List<T>> {
            return Success(list)
        }
        fun error(exception: Throwable) = Error(exception)
    }
}
