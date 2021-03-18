package com.example.clothessize

import com.example.clothessize.model.SizeData
import com.example.clothessize.model.SizeDataDao


class AnthropometryRepository(private val dao: SizeDataDao) {

    suspend fun getSizeData(): List<SizeData> = dao.getAll()


    suspend fun setSizeData(sizeData: SizeData) {
        dao.deleteAll()
        dao.insert(sizeData)
    }
}