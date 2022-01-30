package com.example.test.di

import com.example.test.data.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepository( backendApi = get(), dao = get()) }
}