package com.example.clothessize.presenters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clothessize.R
import com.example.clothessize.databinding.ActivityBrandListBinding
import com.example.clothessize.model.BlandData
import com.example.clothessize.presenters.adapter.BrandListAdapter
import com.example.clothessize.presenters.viewmodel.AnthropometryViewModel
import com.example.clothessize.presenters.viewmodel.BrandListViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel


class BrandListActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context) =
            Intent(context, BrandListActivity::class.java)

    }

    private lateinit var brandListAdapter: BrandListAdapter
    private val brandListViewModel: BrandListViewModel by viewModel()


    private val binding: ActivityBrandListBinding by lazy {
        DataBindingUtil.inflate<ActivityBrandListBinding>(
            layoutInflater,
            R.layout.activity_brand_list,
            null,
            false
        ).also {
            it.lifecycleOwner = this
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.brandList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.brandList.layoutManager = layoutManager
        brandListAdapter = BrandListAdapter(this, brandListViewModel);
        binding.brandList.adapter = brandListAdapter
        setContentView(binding.root)
        val brandArray: ArrayList<BlandData> = arrayListOf()
        val database = Firebase.database.reference
        database.child("brand_list").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            for (item in it.children) {
                val brandData = item.getValue(BlandData::class.java)
                if (brandData != null) {
                    brandArray.add(brandData)
                    brandListAdapter.submitList(brandArray)
                }
            }
        }.addOnFailureListener {
            Log.i("firebase_brand_list", "Got value ${it}")
        }
    }

    override fun onResume() {
        super.onResume()

    }
}