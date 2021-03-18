package com.example.clothessize.presenters

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
        val errorMessageObserver = Observer<String> { errorMessage ->
            showToast(errorMessage);
        }
        anthropometryViewModel.errorMessage.observe(this, errorMessageObserver)
        val isFinishObserver = Observer<Boolean> { isFinish ->
            if (isFinish) {
                finish()
            }
        }
        anthropometryViewModel.isFinish.observe(this, isFinishObserver)
        anthropometryViewModel.getSizeData()
    }


    private fun showToast(toastMessage: String) {
        val toast: Toast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG)
        toast.show()
    }

}
