package com.example.clothessize.presenters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothessize.usecase.AnthropometryUseCase
import com.example.clothessize.model.SizeData
import kotlinx.coroutines.launch


class AnthropometryViewModel(
    private val anthropometryUseCase: AnthropometryUseCase
) : ViewModel() {

    var shoulderWidth = MutableLiveData<String>("")
    var sleeveLength = MutableLiveData<String>("")
    var chestCircumference = MutableLiveData<String>("")
    var dressLength = MutableLiveData<String>("")
    var west = MutableLiveData<String>("")
    var hips = MutableLiveData<String>("")
    var rise = MutableLiveData<String>("")
    var inseam = MutableLiveData<String>("")
    var thigh = MutableLiveData<String>("")
    var hemLength = MutableLiveData<String>("")

    var isFinish = MutableLiveData<Boolean>(false)


    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getSizeData() {
        viewModelScope.launch {
            val listSizeData = anthropometryUseCase.getSizeData()
            //空チェックのみ
            if (listSizeData.isNotEmpty()) {
                val sizeData = listSizeData[0]
                shoulderWidth.value = sizeData.shoulder_width.toString()
                sleeveLength.value = sizeData.sleeve_length.toString()
                chestCircumference.value = sizeData.chest_circumference.toString()
                dressLength.value = sizeData.dress_length.toString()
                west.value = sizeData.west.toString()
                hips.value = sizeData.hips.toString()
                rise.value = sizeData.rise.toString()
                inseam.value = sizeData.inseam.toString()
                thigh.value = sizeData.thigh.toString()
                hemLength.value = sizeData.hem_length.toString()
            }
        }
    }

    //保存時に不正な値が無いか確認処理
    fun confirmationSizeData() {
        if (isSetSizeData()) {
            errorMessage.postValue("未記入箇所があります。全て記入をお願いします。");
        } else {
            val sizeData = SizeData(
                0,
                shoulder_width = shoulderWidth.value?.toInt() ?: 0,
                sleeve_length = sleeveLength.value?.toInt() ?: 0,
                chest_circumference = chestCircumference.value?.toInt() ?: 0,
                dress_length = dressLength.value?.toInt() ?: 0,
                west = west.value?.toInt() ?: 0,
                hips = hips.value?.toInt() ?: 0,
                rise = rise.value?.toInt() ?: 0,
                inseam = inseam.value?.toInt() ?: 0,
                thigh = thigh.value?.toInt() ?: 0,
                hem_length = hemLength.value?.toInt() ?: 0
            )
            //不正がなければ保存処理を実施
            setSizeData(sizeData)
        }

    }

    //サイズデータ保存処理
    private fun setSizeData(sizeData: SizeData) {
        viewModelScope.launch {
            anthropometryUseCase.setSizeData(sizeData)
            isFinish.postValue(true)
        }
    }

    private fun isSetSizeData(): Boolean {
        return shoulderWidth.value?.isBlank() ?: false &&
                sleeveLength.value?.isBlank() ?: false &&
                chestCircumference.value?.isBlank() ?: false &&
                dressLength.value?.isBlank() ?: false &&
                west.value?.isBlank() ?: false &&
                hips.value?.isBlank() ?: false &&
                rise.value?.isBlank() ?: false &&
                inseam.value?.isBlank() ?: false &&
                thigh.value?.isBlank() ?: false &&
                hemLength.value?.isBlank() ?: false;

    }

}