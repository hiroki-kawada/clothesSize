package com.example.clothessize.di

import com.example.clothessize.AnthropometryRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { AnthropometryRepository(get()) }

}