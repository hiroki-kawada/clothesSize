package com.example.clothessize.di

import com.example.clothessize.usecase.AnthropometryUseCase
import com.example.clothessize.usecase.BlandListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { AnthropometryUseCase(get()) }
    single { BlandListUseCase(get()) }
}