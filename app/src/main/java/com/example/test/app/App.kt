package com.example.test.app

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import com.example.test.di.dbModule
import com.example.test.di.networkModule
import com.example.test.di.repositoryModule
import com.example.test.di.viewModelModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                viewModelModule,
                repositoryModule,
                networkModule,
                dbModule,
            )
        }
    }
}