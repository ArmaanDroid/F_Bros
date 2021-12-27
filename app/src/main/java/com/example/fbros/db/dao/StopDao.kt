package com.example.fbros.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fbros.base.BaseDao
import com.example.fbros.model.Stop
import kotlinx.coroutines.flow.Flow

@Dao
interface StopDao : BaseDao<Stop> {
   @Query("Select * from stops")
    fun getAllStops() : Flow<List<Stop>>

    @Query("Select * from stops where id = :stopId")
    fun getStopById(stopId: Int): Flow<Stop>

}