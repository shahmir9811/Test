package com.example.test.data.network

import com.example.test.data.model.dbModel.User
import com.example.test.data.model.dbModel.Users
import com.example.test.data.model.networkModel.ResultsResponse
import com.example.test.data.model.networkModel.StreetResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BackendApiImpl (private val service: UserService) : BackendApi {

    override suspend fun getUsers(page: Int): Users = withContext(Dispatchers.Default) {
       val userResponseList = service.getUsers(page)

       userResponseList.results.map {
            convertToUser(it)
        }
    }

    private fun convertToUser(response: ResultsResponse): User {
        response.apply {
            val id = id.name + id.value
            val name = name.title + " " + name.first + " " + name.last

            val streetResponse: StreetResponse = location.street
            val address = streetResponse.number.toString() + " " + location.city +
                    " " + streetResponse.name

            return User(id = id, pictureUrl = picture.url, name = name,
                        gender = gender, phoneNumber = phoneNumber, address = address,
                        country = location.country)
        }
    }
}