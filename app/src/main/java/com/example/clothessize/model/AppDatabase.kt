package com.example.clothessize.model

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [SizeData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sizeDataDao(): SizeDataDao

}