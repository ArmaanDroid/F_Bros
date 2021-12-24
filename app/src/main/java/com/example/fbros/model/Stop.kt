package com.example.fbros.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stops")
data class Stop(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name")  val name: String,
    @ColumnInfo(name = "color")  val color: String,
    @ColumnInfo(name = "location")  val location: String,
//    @ColumnInfo(name = "pic_list") val picList: List<Int>
    )
