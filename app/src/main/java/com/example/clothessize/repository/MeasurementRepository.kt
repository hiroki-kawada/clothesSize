package com.example.clothessize.repository

import com.example.clothessize.model.SizeData
import com.example.clothessize.model.SizeDataDao

class MeasurementRepository(private val dao: SizeDataDao) {

    suspend fun getSizeData(): List<SizeData> = dao.getAll()
}