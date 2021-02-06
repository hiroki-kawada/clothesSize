package com.example.clothessize.di

import android.app.Application
import androidx.room.Room
import com.example.clothessize.model.AppDatabase
import com.example.clothessize.model.SizeDataDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val DatabaseModule = module {
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "clothesSize")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun sizeDataCountriesDao(database: AppDatabase): SizeDataDao {
        return database.sizeDataDao()
    }

    single { provideDatabase(androidApplication()) }
    single { sizeDataCountriesDao(get()) }
}