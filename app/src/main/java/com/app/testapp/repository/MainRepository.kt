package com.app.testapp.repository

import com.app.testapp.api.ApiService
import com.app.testapp.api.response.GitHubUserListResponseItem
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getUserRepositories(): Result<List<GitHubUserListResponseItem>> {

        return try {

            val response =
                apiService.getGitHubUserRepos().body() ?: emptyList()

            Result.success(response)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }
}