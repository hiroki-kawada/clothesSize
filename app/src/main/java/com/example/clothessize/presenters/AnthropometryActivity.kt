package com.example.clothessize.presenters

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.clothessize.R
import com.example.clothessize.databinding.ActivityAnthropometryBinding

class AnthropometryActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context) =
            Intent(context, AnthropometryActivity::class.java)

    }


    private val binding: ActivityAnthropometryBinding by lazy {
        DataBindingUtil.inflate<ActivityAnthropometryBinding>(
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
