package com.example.clothessize.presenters.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.clothessize.SingleLiveEvent
import com.example.clothessize.model.BlandData
import com.example.clothessize.usecase.BlandListUseCase
import kotlinx.coroutines.launch

class BrandListViewModel(
    private val blandListUseCase: BlandListUseCase
) : ViewModel() {

    private val showMeasurementEvent = SingleLiveEvent<BlandData>()
    fun getShowMeasurementEvent(): SingleLiveEvent<BlandData> {
        return showMeasurementEvent
    }

    /**
     * アイテムクリック処理
     */
    fun onItemCrick(brandData: BlandData) {
        showMeasurementEvent.postValue(brandData)
    }


}