package com.example.clothessize.presenters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clothessize.R
import com.example.clothessize.model.CategoryData
import com.example.clothessize.usecase.MeasurementUseCase

class MeasurementViewModel(
    private val measurementUseCase: MeasurementUseCase
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

    var selectCategory = MutableLiveData<CategoryData>(CategoryData("", ""))

    //チェックボックスグループ
    var radioChecked = MutableLiveData<Int>(R.id.siz_s_radio_button)

    //カテゴリーリスト
    val categoryArray: ArrayList<CategoryData> = arrayListOf()


}