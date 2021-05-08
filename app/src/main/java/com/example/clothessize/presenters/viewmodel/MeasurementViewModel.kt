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

            val difference = lowerBodyData.west - mSizeData.west

            if (-5 <= difference && difference <= 5) {
                //ちょうど良い
                measurementStringBuilder.append("ウエストはちょうど良いです。\n")
            } else if (-5 > difference) {
                //ウエストが細い場合
                measurementStringBuilder.append("ウエストはきついです。\n")
            } else {
                //ウエストが太い場合
                measurementStringBuilder.append("ウエストは緩いです。\n")
            }
        }

        //ヒップ計測
        if (lowerBodyData.hips == 0) {
            //服側にデータがない
            measurementStringBuilder.append("ヒップデータはありません。\n")
        } else {

            val difference = lowerBodyData.hips - mSizeData.hips

            if (-5 <= difference && difference <= 5) {
                //ちょうど良い
                measurementStringBuilder.append("ヒップはちょうど良いです。\n")
            } else if (-5 > difference) {
                //ヒップ が細い場合
                measurementStringBuilder.append("ヒップはきついです。\n")
            } else {
                //ヒップが太い場合
                measurementStringBuilder.append("ヒップは緩いです。\n")
            }
        }

        //股上計測
        if (lowerBodyData.rise == 0) {
            //服側にデータがない
            measurementStringBuilder.append("股上データはありません。\n")
        } else {

            val difference = lowerBodyData.rise - mSizeData.rise

            if (-5 <= difference && difference <= 5) {
                //ちょうど良い
                measurementStringBuilder.append("股上はちょうど良いです。\n")
            } else if (-5 > difference) {
                //股上が短い場合
                measurementStringBuilder.append("股上は短いです。\n")
            } else {
                //股上が長い場合
                measurementStringBuilder.append("股上は長いです。\n")
            }
        }

        //股下計測
        if (lowerBodyData.inseam == 0) {
            //服側にデータがない
            measurementStringBuilder.append("股下データはありません。\n")
        } else {

            val difference = lowerBodyData.inseam - mSizeData.inseam

            if (-5 <= difference && difference <= 5) {
                //ちょうど良い
                measurementStringBuilder.append("股下はちょうど良いです。\n")
            } else if (-5 > difference) {
                //股下が短い場合
                measurementStringBuilder.append("股下は短いです。\n")
            } else {
                //股下が長い場合
                measurementStringBuilder.append("股下は長いです。\n")
            }
        }

        //ワタリ計測
        if (lowerBodyData.thigh == 0) {
            //服側にデータがない
            measurementStringBuilder.append("ワタリデータはありません。\n")
        } else {

            val difference = lowerBodyData.thigh - mSizeData.thigh

            if (-5 <= difference && difference <= 5) {
                //ちょうど良い
                measurementStringBuilder.append("ワタリはちょうど良いです。\n")
            } else if (-5 > difference) {
                //股下が短い場合
                measurementStringBuilder.append("ワタリはキツイです。\n")
            } else {
                //股下が長い場合
                measurementStringBuilder.append("ワタリは緩いです。\n")
            }
        }

        //裾周り計測
        if (lowerBodyData.hem_length == 0) {
            //服側にデータがない
            measurementStringBuilder.append("裾周りデータはありません。")
        } else {

            val difference = lowerBodyData.hem_length - mSizeData.hem_length

            if (-5 <= difference && difference <= 5) {
                //ちょうど良い
                measurementStringBuilder.append("裾周りはちょうど良いです。")
            } else if (-5 > difference) {
                //股下が短い場合
                measurementStringBuilder.append("裾周りはキツイです。")
            } else {
                //股下が長い場合
                measurementStringBuilder.append("裾周りは緩いです。")
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
            measurementStringBuilder.append("肩幅データはありません。\n")
        } else {

            val difference = upperBodyData.shoulder_width - mSizeData.shoulder_width

            if (-5 <= difference && difference <= 5) {
                //ちょうど良い
                measurementStringBuilder.append("肩幅はちょうど良いです。\n")
            } else if (-5 > difference) {
                //服の肩幅が長い
                measurementStringBuilder.append("肩幅は小さいです。\n")
            } else {
                //服の肩幅が狭い
                measurementStringBuilder.append("肩幅は大きいです。\n")
            }
        }
        //袖丈計測
        if (upperBodyData.sleeve_length == 0) {
            //服側にデータがない
            measurementStringBuilder.append("袖丈データはありません。")
        } else {

            val difference = upperBodyData.sleeve_length - mSizeData.sleeve_length

            if (-5 <= difference && difference <= 5) {
                //ちょうど良い
                measurementStringBuilder.append("裾丈はちょうど良いです。\n")
            } else if (-5 > difference) {
                //服の袖丈が短い
                measurementStringBuilder.append("裾丈は短いです。\n")
            } else {
                //服の袖丈が長い
                measurementStringBuilder.append("裾丈は長いです。\n")

            }

        }
        //胸囲計測
        if (upperBodyData.chest_circumference == 0) {
            //服側にデータがない
            measurementStringBuilder.append("胸囲データはありません。")
        } else {
            val difference = upperBodyData.chest_circumference - mSizeData.chest_circumference

            if (-5 <= difference && difference <= 5) {
                //ちょうど良い
                measurementStringBuilder.append("胸囲はちょうど良いです。\n")
            } else if (-5 > difference) {
                //胸囲が狭い場合
                measurementStringBuilder.append("胸囲はきついです。\n")
            } else {
                //胸囲が広い場合
                measurementStringBuilder.append("胸囲はゆるいです。\n")

            }

        }
        //着丈計測
        if (upperBodyData.dress_length == 0) {
            //服側にデータがない
            measurementStringBuilder.append("着丈データはありません。")
        } else {

            val difference = upperBodyData.dress_length - mSizeData.dress_length

            if (-5 <= difference && difference <= 5) {
                //ちょうど良い
                measurementStringBuilder.append("着丈はちょうど良いです。")
            } else if (-5 > difference) {
                //胸囲が狭い場合
                measurementStringBuilder.append("着丈は短いです。")
            } else {
                //胸囲が広い場合
                measurementStringBuilder.append("着丈は長いです。")

            }
        }
        measurementResultUpperBodyMessage.postValue(measurementStringBuilder.toString())
    }
}