package com.example.clothessize.presenters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothessize.R
import com.example.clothessize.model.CategoryData
import com.example.clothessize.model.LowerBodyData
import com.example.clothessize.model.SizeData
import com.example.clothessize.model.UpperBodyData
import com.example.clothessize.usecase.MeasurementUseCase
import kotlinx.coroutines.launch
import java.lang.StringBuilder

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

    //下半身計測データ
    var measurementResultLowerBodyMessage = MutableLiveData<String>("");

    //上半身計測データ
    var measurementResultUpperBodyMessage = MutableLiveData<String>("");

    lateinit var mSizeData: SizeData


    fun getSizeData() {
        viewModelScope.launch {
            val listSizeData = measurementUseCase.getSizeData()
            //空チェックのみ
            if (listSizeData.isNotEmpty()) {
                mSizeData = listSizeData[0]
                shoulderWidth.value = mSizeData.shoulder_width.toString()
                sleeveLength.value = mSizeData.shoulder_width.toString()
                chestCircumference.value = mSizeData.shoulder_width.toString()
                dressLength.value = mSizeData.shoulder_width.toString()

                west.value = mSizeData.shoulder_width.toString()
                hips.value = mSizeData.shoulder_width.toString()
                rise.value = mSizeData.shoulder_width.toString()
                inseam.value = mSizeData.shoulder_width.toString()
                thigh.value = mSizeData.shoulder_width.toString()
                hemLength.value = mSizeData.shoulder_width.toString()
            }
        }
    }

    /**
     * 下半身計測
     */
    fun measurementLowerBodyData(lowerBodyData: LowerBodyData) {
        val measurementStringBuilder = StringBuilder()
        //ウエスト計測
        if (lowerBodyData.west == 0) {
            //服側にデータがない
            measurementStringBuilder.append("ウエストデータはありません。\n")
        } else {
            if (lowerBodyData.west < mSizeData.west) {
                //ウエストが細い場合
                measurementStringBuilder.append("ウエストが細いです。\n")
            } else {
                //ウエストが太い場合
                measurementStringBuilder.append("ウエストが太いです。\n")
            }
        }

        //ヒップ計測
        if (lowerBodyData.hips == 0) {
            //服側にデータがない
            measurementStringBuilder.append("ヒップデータはありません。\n")
        } else {
            if (lowerBodyData.hips < mSizeData.hips) {
                //ヒップが細い場合
                measurementStringBuilder.append("ヒップが細いです。\n")
            } else {
                //ヒップが太い場合
                measurementStringBuilder.append("ヒップが太いです。\n")
            }
        }

        //股上計測
        if (lowerBodyData.rise == 0) {
            //服側にデータがない
            measurementStringBuilder.append("股上データはありません。\n")
        } else {
            if (lowerBodyData.rise < mSizeData.rise) {
                //股上が長い場合
                measurementStringBuilder.append("股上が長いです。\n")
            } else {
                //股上が短い場合
                measurementStringBuilder.append("股上が短いです。\n")
            }
        }

        //股下計測
        if (lowerBodyData.inseam == 0) {
            //服側にデータがない
            measurementStringBuilder.append("股下データはありません。\n")
        } else {
            if (lowerBodyData.inseam < mSizeData.inseam) {
                //股下が長い場合
                measurementStringBuilder.append("股下が長いです。\n")
            } else {
                //股下が短い場合
                measurementStringBuilder.append("股下が短いです。\n")
            }
        }

        //ワタリ計測
        if (lowerBodyData.thigh == 0) {
            //服側にデータがない
            measurementStringBuilder.append("ワタリデータはありません。\n")
        } else {
            if (lowerBodyData.thigh < mSizeData.thigh) {
                //ワタリが細い場合
                measurementStringBuilder.append("ワタリが細いです。\n")
            } else {
                //ワタリが太い場合
                measurementStringBuilder.append("ワタリが太いです。\n")
            }
        }

        //裾丈計測
        if (lowerBodyData.hem_length == 0) {
            //服側にデータがない
            measurementStringBuilder.append("裾丈データはありません。")
        } else {
            if (lowerBodyData.hem_length < mSizeData.hem_length) {
                //ウエストが細い場合
                measurementStringBuilder.append("裾丈が細いです。")
            } else {
                //ウエストが太い場合
                measurementStringBuilder.append("裾丈が太いです。")
            }
        }

        measurementResultLowerBodyMessage.postValue(measurementStringBuilder.toString())
    }

    /**
     * 上半身計測
     */
    fun measurementUpperBodyData(upperBodyData: UpperBodyData) {
        val measurementStringBuilder = StringBuilder()

        //肩幅計測
        if (upperBodyData.shoulder_width == 0) {
            //服側にデータがない
            measurementStringBuilder.append("肩幅データはありません。")
        } else {
            if (upperBodyData.shoulder_width < mSizeData.shoulder_width) {
                //肩幅が広い場合
                measurementStringBuilder.append("肩幅が広いです。")
            } else {
                //肩幅が狭い場合
                measurementStringBuilder.append("肩幅が狭いです。")
            }
        }
        //袖丈計測
        if (upperBodyData.sleeve_length == 0) {
            //服側にデータがない
            measurementStringBuilder.append("袖丈データはありません。")
        } else {
            if (upperBodyData.sleeve_length < mSizeData.sleeve_length) {
                //袖丈が長い場合
                measurementStringBuilder.append("裾丈が短いです。")
            } else {
                //袖丈が太い場合
                measurementStringBuilder.append("裾丈が長いです。")
            }
        }
        //胸囲計測
        if (upperBodyData.chest_circumference == 0) {
            //服側にデータがない
            measurementStringBuilder.append("胸囲データはありません。")
        } else {
            if (upperBodyData.chest_circumference < mSizeData.chest_circumference) {
                //胸囲が広い場合
                measurementStringBuilder.append("胸囲が広いです。")
            } else {
                //胸囲が狭い場合
                measurementStringBuilder.append("胸囲が狭いです。")
            }
        }
        //着丈計測
        if (upperBodyData.dress_length == 0) {
            //服側にデータがない
            measurementStringBuilder.append("着丈データはありません。")
        } else {
            if (upperBodyData.dress_length < mSizeData.dress_length) {
                //着丈が短い場合
                measurementStringBuilder.append("着丈が短いです。")
            } else {
                //着丈が長い場合
                measurementStringBuilder.append("着丈が長いです。")
            }
        }
        measurementResultUpperBodyMessage.postValue(measurementStringBuilder.toString())
    }
}