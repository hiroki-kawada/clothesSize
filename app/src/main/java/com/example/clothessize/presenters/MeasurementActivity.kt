package com.example.clothessize.presenters

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.clothessize.R
import com.example.clothessize.databinding.ActivityMeasurementBinding
import com.example.clothessize.model.BlandData
import com.example.clothessize.model.CategoryData
import com.example.clothessize.presenters.viewmodel.MeasurementViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.StringBuilder

class MeasurementActivity : AppCompatActivity() {


    companion object {
        private const val BLAND_DATA = "BlandData"

        fun newInstance(context: Context, blandKey: String): Intent {
            return Intent(context, MeasurementActivity::class.java)
                .putExtra(BLAND_DATA, blandKey)
        }
    }

    //サイズコード
    private var sizeCode = "s"


    //ViewModel
    private val measurementViewModel: MeasurementViewModel by viewModel()

    private val binding: ActivityMeasurementBinding by lazy {
        DataBindingUtil.inflate<ActivityMeasurementBinding>(
            layoutInflater,
            R.layout.activity_measurement,
            null,
            false
        ).also {
            it.lifecycleOwner = this
            it.viewModel = measurementViewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val database = Firebase.database.reference
        val text = intent.getStringExtra(BLAND_DATA)
        binding.upperBody.visibility = View.GONE
        binding.lowerBody.visibility = View.GONE

        if (text.isNullOrBlank()) {
            val categoryUpperBodyData = CategoryData("上半身", "upper_body", 0)
            val categoryLowerBodyData = CategoryData("下半身", "lower_body", 1)
            measurementViewModel.categoryArray.add(categoryUpperBodyData)
            measurementViewModel.categoryArray.add(categoryLowerBodyData)
            binding.sizeRadioGroup.visibility = View.GONE
        }

        measurementViewModel.radioChecked.observe(this, Observer {
            when (it) {
                R.id.siz_s_radio_button -> {
                    sizeCode = "s"
                }
                R.id.siz_m_radio_button -> {
                    sizeCode = "m"
                }
                R.id.siz_l_radio_button -> {
                    sizeCode = "l"
                }
                R.id.siz_xl_radio_button -> {
                    sizeCode = "xl"
                }
            }
        })

        binding.categoryText.setOnClickListener {
            val readmeFragment = CategoryListFragment()
            readmeFragment.show(supportFragmentManager, "")
        }

        if (!text.isNullOrBlank()) {
            database.child("clothing_category").child(text).get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                for (item in it.children) {
                    val brandData = item.getValue(CategoryData::class.java)
                    if (brandData != null) {
                        measurementViewModel.categoryArray.add(brandData)
                    }
                }
            }.addOnFailureListener {
                Log.i("firebase_brand_list", "Got value ${it}")
            }
        }


        binding.measurementButton.setOnClickListener {
            val categoryData = measurementViewModel.selectCategory.value
            if (categoryData == null) {
                return@setOnClickListener
            } else {
                val keyCategory = if (categoryData.category_key == 0) {
                    "lower_body"
                } else {
                    "upper_body"
                }

                val categorySizeKey =
                    StringBuilder().append(categoryData.size_key).append("_").append(sizeCode)
                database.child("clothing_size").child(keyCategory).child(categorySizeKey.toString())
                    .get().addOnSuccessListener {
                        Log.i("firebase", "Got value ${it.value}")


                    }.addOnFailureListener {
                        Log.i("firebase_brand_list", "Got value ${it}")
                    }
            }
        }


    }
}