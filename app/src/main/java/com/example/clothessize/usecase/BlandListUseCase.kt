package com.example.clothessize.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.example.clothessize.model.BlandData
import com.example.clothessize.repository.BlandListRepository

class BlandListUseCase(
    private val blandListRepository: BlandListRepository
) {

}