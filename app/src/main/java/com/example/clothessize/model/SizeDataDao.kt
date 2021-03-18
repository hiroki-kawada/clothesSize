package com.example.clothessize.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SizeDataDao {

    @Insert
    suspend fun insert(sizeData: SizeData)

    @Update
    suspend fun update(sizeData: SizeData)

    @Query("DELETE FROM size_table")
    fun deleteAll()

    @Query("SELECT * FROM size_table")
    suspend fun getAll(): List<SizeData>
}