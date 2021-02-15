package com.example.clothessize.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "size_table")
data class SizeData(
    @PrimaryKey val id: Int,
    val shoulder_width: Int,      //肩幅
    val sleeve_length: Int,   //袖丈
    val chest_circumference: Int,   //胸囲
    val dress_length: Int,   //着丈
    val west: Int,   //ウエスト
    val hips: Int,   //ヒップ
    val rise: Int,   //股上
    val inseam: Int,   //股下
    val thigh: Int,   //ワタリ
    val hem_length: Int   //裾丈
)