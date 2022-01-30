package com.example.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.data.model.dbModel.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class DB : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}