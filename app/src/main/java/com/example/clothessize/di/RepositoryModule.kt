package com.example.clothessize.di

import com.example.clothessize.repository.AnthropometryRepository
import com.example.clothessize.repository.BlandListRepository
import com.example.clothessize.repository.MeasurementRepository
import com.example.clothessize.usecase.AnthropometryUseCase
import org.koin.dsl.module

val repositoryModule = module {

    single { AnthropometryRepository(get()) }
    single { BlandListRepository() }
    single { MeasurementRepository(get()) }

}