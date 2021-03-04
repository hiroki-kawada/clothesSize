package com.example.clothessize.di

import com.example.clothessize.AnthropometryUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { AnthropometryUseCase(get()) }
}