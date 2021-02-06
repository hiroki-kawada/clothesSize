package com.example.clothessize.presenters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.clothessize.R
import com.example.clothessize.databinding.ActivityTopBinding

class TopActivity : AppCompatActivity() {

    private val binding: ActivityTopBinding by lazy {
        DataBindingUtil.inflate<ActivityTopBinding>(
            layoutInflater,
            R.layout.activity_top,
            null,
            false
        ).also {
            it.lifecycleOwner = this
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            //身体情報編集、保存画面に遷移
            val intent = AnthropometryActivity.newInstance(this)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            //ブランドリスト画面に遷移
            val intent = BrandListActivity.newInstance(this)
            startActivity(intent)
        }

    }

}