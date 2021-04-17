package com.example.clothessize.presenters.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.clothessize.model.BlandData
import com.example.clothessize.usecase.BlandListUseCase
import kotlinx.coroutines.launch

class BrandListViewModel(
    private val blandListUseCase: BlandListUseCase
) : ViewModel() {


    fun onItemCrick(brandData: BlandData) {
    }


}