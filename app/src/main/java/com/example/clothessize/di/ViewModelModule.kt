package com.example.clothessize.di

import com.example.clothessize.presenters.viewmodel.AnthropometryViewModel
import com.example.clothessize.presenters.viewmodel.BrandListViewModel
import com.example.clothessize.presenters.viewmodel.MeasurementViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AnthropometryViewModel(get()) }
    viewModel { BrandListViewModel(get()) }
    viewModel { MeasurementViewModel(get()) }
}