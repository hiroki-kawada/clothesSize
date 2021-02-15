package com.example.clothessize.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SizeDataDao {

    @Insert
    fun insert(sizeData: SizeData)

    @Update
    fun update(sizeData: SizeData)

    @Delete
    fun delete(sizeData: SizeData)


    @Query("SELECT * FROM size_table")
    fun getAll(): LiveData<List<SizeData>>
}