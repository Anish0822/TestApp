package com.app.testapp.api

import com.app.testapp.api.response.GitHubUserListResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users/octocat/repos")
    suspend fun getGitHubUserRepos(): Response<List<GitHubUserListResponseItem>>

}
