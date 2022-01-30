package com.example.test.data.network

import com.example.test.data.model.networkModel.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api?seed=renderforest&results=20")
    suspend fun getUsers(
        @Query("page") page: Int
    ): UserResponse
}