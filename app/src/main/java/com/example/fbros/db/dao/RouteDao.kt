package com.example.fbros.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fbros.base.BaseDao
import com.example.fbros.model.Route
import kotlinx.coroutines.flow.Flow

@Dao
interface RouteDao : BaseDao<Route> {

    @Query("Select * from routes")
    fun getAllRoutes(): Flow<List<Route>>

    @Query("Select * from routes where id = :routeId")
    fun getRouteById(routeId: Int): Flow<Route>


}