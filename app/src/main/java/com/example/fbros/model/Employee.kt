package com.example.fbros.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Gender true = male, false = female
 */
@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name")  val name: String,
    @ColumnInfo(name = "gender")   val gender: Boolean,
    @ColumnInfo(name = "email_id")  val emailId: String,
    @ColumnInfo(name = "phone_number")   val phoneNumber: String,
    @ColumnInfo(name = "pic")   val pic: String = "picAddress",
    @ColumnInfo(name = "address")   val address: String
)



