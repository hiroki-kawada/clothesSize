package com.example.clothessize.usecase

import com.example.clothessize.model.SizeData
import com.example.clothessize.repository.MeasurementRepository

class MeasurementUseCase(
    private val measurementRepository: MeasurementRepository
) {
    suspend fun getSizeData(): List<SizeData> {
        return measurementRepository.getSizeData()
    }
}