package com.example.clothessize.presenters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothessize.AnthropometryUseCase
import com.example.clothessize.model.SizeData
import kotlinx.coroutines.launch

class AnthropometryViewModel(
    private val anthropometryUseCase: AnthropometryUseCase
) : ViewModel() {

    var shoulderWidth: String = ""



    fun getSizeData() {
        viewModelScope.launch {
            val listSizeData = anthropometryUseCase.getSizeData()
            //空チェックのみ
            if (listSizeData.isEmpty()) {
                val sizeData = listSizeData[0]
            }
        }
    }

    //保存時に不正な値が無いか確認処理
    fun confirmationSizeData(sizeData: SizeData) {
        //不正がなければ保存処理を実施
        setSizeData(sizeData)
    }

    //サイズデータ保存処理
    private fun setSizeData(sizeData: SizeData) {
        viewModelScope.launch {
            anthropometryUseCase.setSizeData(sizeData)
        }
    }

}