package com.example.test.di

import android.app.Application
import androidx.room.Room
import com.example.test.data.db.DB
import com.example.test.data.db.UserDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DATABASE_NAME = "db"

val dbModule = module {
    single { getDB(androidApplication()) }
    single { getUserDao(get()) }
}

private fun getDB(application: Application): DB {
    return Room.databaseBuilder(application, DB::class.java, DATABASE_NAME)
        .build()
}

private fun getUserDao(database: DB): UserDao {
    return database.getUserDao()
}
