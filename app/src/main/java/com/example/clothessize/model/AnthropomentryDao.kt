package com.example.clothessize.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AnthropomentryDao {

    @Insert
    fun insert(user: AnthropomentryData)

    @Update
    fun update(user: AnthropomentryData)

    @Delete
    fun delete(user: AnthropomentryData)


    @Query("SELECT * FROM anthropomentry")
    fun getAll(): LiveData<List<AnthropomentryData>>
}