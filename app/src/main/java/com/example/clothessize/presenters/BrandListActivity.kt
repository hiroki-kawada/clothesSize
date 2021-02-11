package com.example.clothessize.presenters

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.clothessize.R
import com.example.clothessize.databinding.ActivityBrandListBinding

class BrandListActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context) =
            Intent(context, BrandListActivity::class.java)

    }

    private val binding: ActivityBrandListBinding by lazy {
        DataBindingUtil.inflate<ActivityBrandListBinding>(
            layoutInflater,
            R.layout.activity_anthropometry,
            null,
            false
        ).also {
            it.lifecycleOwner = this
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}