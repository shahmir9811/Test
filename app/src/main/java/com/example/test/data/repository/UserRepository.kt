package com.example.test.data.repository

import com.example.test.data.db.UserDao
import com.example.test.data.model.dbModel.User
import com.example.test.data.model.dbModel.Users
import com.example.test.data.network.BackendApiImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val backendApi: BackendApiImpl,
    private val dao: UserDao
) {

    val savedUsers: Flow<Result<Users>>
        get() = dao.getAllUsers().map { users ->
            Result.successList(users)
        }

    suspend fun addUser(user: User) {
        dao.insertUser(user)
    }

    suspend fun removeUser(user: User) {
        dao.deleteUser(user)
    }

    fun getUsersPage(page: Int): Flow<Result<Users>> = flow {
        emit(Result.loading())

        val users = backendApi.getUsers(page)

        emit(Result.successList(users))
    }.catch { e ->
        emit(Result.error(e))
    }
}