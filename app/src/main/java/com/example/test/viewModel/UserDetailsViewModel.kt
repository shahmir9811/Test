package com.example.test.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.model.dbModel.User
import com.example.test.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserDetailsViewModel(val repository: UserRepository, val user: User) : ViewModel() {

    fun saveUser() {
        viewModelScope.launch {
            repository.addUser(user)
        }
    }

    fun removeUser() {
        viewModelScope.launch {
            repository.removeUser(user)
        }
    }
}