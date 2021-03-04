package com.example.clothessize.presenters

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.clothessize.R
import com.example.clothessize.databinding.ActivityAnthropometryBinding
import com.example.clothessize.presenters.viewmodel.AnthropometryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnthropometryActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context) =
            Intent(context, AnthropometryActivity::class.java)

    }

    private val anthropometryViewModel: AnthropometryViewModel by viewModel()

    private val binding: ActivityAnthropometryBinding by lazy {
        DataBindingUtil.inflate<ActivityAnthropometryBinding>(
            layoutInflater,
            R.layout.activity_anthropometry,
            null,
            false
        ).also {
            it.lifecycleOwner = this
            it.viewModel = anthropometryViewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
