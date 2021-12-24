package com.example.fbros.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "routes",)
data class Route(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")   val id: Int,
    @ColumnInfo(name = "name")   val name: String,
    @ColumnInfo(name = "city")   val city: String,

//    @ColumnInfo(name = "stop_list")   val stopList: List<Int>
    )
