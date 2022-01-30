package com.example.test.data.network

import com.example.test.data.model.dbModel.Users

interface BackendApi {
    suspend fun getUsers(page: Int): Users
}