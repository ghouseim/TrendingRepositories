package com.task.trendingrepos.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.trendingrepos.domain.usecase.ITrendingRepositoryUseCase
import com.task.trendingrepos.ui.mapper.toPresentation
import com.task.trendingrepos.ui.model.TrendingRepositoryUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val trendingRepositoryUseCase: ITrendingRepositoryUseCase
) : ViewModel() {

    val trendingRepoData: LiveData<List<TrendingRepositoryUiModel>>
        get() = _trendingRepoData

    private val _trendingRepoData: MutableLiveData<List<TrendingRepositoryUiModel>> =
        MutableLiveData()

    val isError: LiveData<Boolean>
        get() = _isError

    private val _isError: MutableLiveData<Boolean> =
        MutableLiveData()

    private var trendingRepoResponseData: List<TrendingRepositoryUiModel> = emptyList()


    fun fetchTrendingRepos() {
        viewModelScope.launch {
            trendingRepositoryUseCase.getTrendingRepositories()
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.e("MainViewModel", "Error: ${it.message}")
                    _isError.value = true
                }
                .collect { repoList ->
                    _isError.value = false
                    trendingRepoResponseData = repoList.map { it.toPresentation() }
//                    viewModelScope.launch(Dispatchers.Main) {
                        _trendingRepoData.value = trendingRepoResponseData
//                    }
                }
        }
    }

    fun searchRepo(query: String) {
        if (query.isNotBlank()) {
            val searchList = trendingRepoResponseData.filter { it.name.contains(query, true) }
            _trendingRepoData.value = searchList
        } else {
            _trendingRepoData.value = trendingRepoResponseData
        }
    }

    fun onItemClicked(index: Int, isSelected: Boolean) {
        trendingRepoResponseData[index].isSelected = isSelected
    }
}
