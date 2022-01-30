package com.example.test.data.model.networkModel

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("results") val results: List<ResultsResponse>,
)

data class ResultsResponse(@SerializedName("id") val id: IdResponse,
                           @SerializedName("name") val name: NameResponse,
                           @SerializedName("picture") val picture: PictureResponse,
                           @SerializedName("location") val location: LocationResponse,
                           @SerializedName("gender") val gender: String,
                           @SerializedName("phone") val phoneNumber: String)

data class NameResponse(@SerializedName("title") val title: String,
                        @SerializedName("first") val first: String,
                        @SerializedName("last") val last: String)

data class LocationResponse(@SerializedName("street") val street: StreetResponse,
                            @SerializedName("city") val city: String,
                            @SerializedName("country") val country: String)

data class StreetResponse(@SerializedName("number") val number: Int,
                          @SerializedName("name") val name: String)

data class IdResponse(@SerializedName("name") val name: String,
                          @SerializedName("value") val value: String)

data class PictureResponse(@SerializedName("medium") val url: String)