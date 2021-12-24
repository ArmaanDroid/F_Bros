package com.example.fbros.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fbros.db.dao.EmployeeDao
import com.example.fbros.db.dao.PicDao
import com.example.fbros.db.dao.RouteDao
import com.example.fbros.db.dao.StopDao
import com.example.fbros.model.Employee
import com.example.fbros.model.Pic
import com.example.fbros.model.Route
import com.example.fbros.model.Stop

@Database(entities = [Employee::class, Pic::class, Route::class, Stop::class], version = 1, exportSchema = false)
abstract class FBrosDB : RoomDatabase(){

    // Dao Getters
    abstract fun getEmployeeDao() : EmployeeDao

    abstract fun getPicDao() : PicDao

    abstract fun getRouteDao() : RouteDao

    abstract fun getStopDao() : StopDao

    // To make it singleton
    companion object {
        @Volatile
        private var INSTANCE: FBrosDB? = null
        fun getDatabase(context: Context): FBrosDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FBrosDB::class.java,
                    "f_bros_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}