package com.example.clothessize.presenters

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.clothessize.presenters.viewmodel.MeasurementViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategoryListFragment : DialogFragment() {


    private val measurementViewModel: MeasurementViewModel by sharedViewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val categoryNameArray: ArrayList<String> = arrayListOf()
        for (categoryData in measurementViewModel.categoryArray) {
            categoryData.category_name?.let { categoryNameArray.add(it) }
        }
        categoryNameArray.toTypedArray()

        val builder = AlertDialog.Builder(context)
            .setTitle("カテゴリーを選択してください。")
            .setItems(categoryNameArray.toTypedArray()) { dialog, which ->
                // GENDERSのリストのどれかが選択されたときに実行される処理
                // whichはGENDERS配列の選択されたインデックス
                measurementViewModel.selectCategory.value =
                    measurementViewModel.categoryArray[which]
            }
            .setNegativeButton("閉じる", null)

        return builder.create()
    }
}