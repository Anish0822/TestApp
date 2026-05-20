package com.app.testapp.ui.listscreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.testapp.api.response.GitHubUserListResponseItem
import com.app.testapp.base.BaseViewModel
import com.app.testapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) :
    BaseViewModel<MainNavigator>() {

    fun onSearchClicked() {
        navigator?.onSearchClicked()
    }

    private val _repositories = MutableLiveData<List<GitHubUserListResponseItem>?>()
    val repositories: MutableLiveData<List<GitHubUserListResponseItem>?> get() = _repositories

    private var originalRepositories: List<GitHubUserListResponseItem>? = null

    fun fetchRepositories() {
        if (!repositories.value.isNullOrEmpty()) {
            return
        }
        navigator?.showProgressDialog()
        viewModelScope.launch {
            val result = repository.getUserRepositories()
            navigator?.dismissProgressDialog()
            result.onSuccess { repositories ->
                originalRepositories = repositories
                _repositories.postValue(repositories)
            }.onFailure { exception ->
                Log.e("MainViewModel", exception.message ?: "An unknown error occurred")
            }
        }
    }
}