package com.example.test.di

import com.example.test.data.model.dbModel.User
import com.example.test.viewModel.UserDetailsViewModel
import com.example.test.viewModel.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (user: User) -> UserDetailsViewModel(get(), user) }
    viewModel { UserListViewModel(repository = get()) }
}
