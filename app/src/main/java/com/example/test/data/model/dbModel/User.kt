package com.example.test.data.model.dbModel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Data class that represents a Reddit image.
 */
@Entity(tableName = "users")
@Parcelize
data class User(
    @PrimaryKey
    val id: String,
    val pictureUrl: String,
    val name: String,
    val gender: String,
    val phoneNumber: String,
    val address: String,
    val country: String
) : Parcelable

typealias Users = List<User>
