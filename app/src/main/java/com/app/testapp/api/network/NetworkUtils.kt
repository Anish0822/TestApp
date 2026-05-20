package com.app.testapp.api.network

import retrofit2.Response

fun <T> handleResponse(response: Response<T>): Result<T> {
    return if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            Result.success(body)
        } else {
            Result.failure(Exception("Response body is null"))
        }
    } else {
        Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
    }
}
