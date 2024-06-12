package com.dicoding.restaurantapp

import android.app.Application
import com.dicoding.restaurantapp.core.di.*
import com.dicoding.restaurantapp.di.useCaseModule
import com.dicoding.restaurantapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )

        }
    }
}