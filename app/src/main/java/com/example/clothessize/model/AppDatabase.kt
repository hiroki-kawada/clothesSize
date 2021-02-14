package com.example.clothessize.model

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [AnthropomentryData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun anthropomentryDao(): AnthropomentryDao

}