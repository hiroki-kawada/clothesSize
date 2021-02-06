package com.example.clothessize.usecase

import com.example.clothessize.repository.AnthropometryRepository
import com.example.clothessize.model.SizeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnthropometryUseCase(
    private val anthropometryRepository: AnthropometryRepository
) {

    suspend fun getSizeData(): List<SizeData> {
        return anthropometryRepository.getSizeData()
    }

    suspend fun setSizeData(sizeData: SizeData) {
        withContext(Dispatchers.IO) {
            anthropometryRepository.setSizeData(sizeData)
        }
    }
}