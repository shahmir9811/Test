package com.example.test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.test.data.model.dbModel.Users
import com.example.test.data.repository.Result
import com.example.test.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

const val TIME_OUT = 20*1000L

class UserListViewModel(repository: UserRepository) : ViewModel() {

    private val pageFlow = MutableStateFlow(1)

    val savedUsers: LiveData<Result<Users>> = repository.savedUsers.asLiveData()

    val users: LiveData<Result<Users>> = pageFlow
        .debounce(TIME_OUT)
        .flatMapLatest { page ->
            repository.getUsersPage(page)
        }
        .asLiveData()

    fun loadPage(page: Int) {
        pageFlow.value = page
    }
}