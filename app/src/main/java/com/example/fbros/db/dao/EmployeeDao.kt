package com.example.fbros.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fbros.base.BaseDao
import com.example.fbros.model.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao : BaseDao<Employee> {

    @Query("Select * from employees")
    fun getEmployees() : Flow<List<Employee>>

    @Query("Select * from employees where id = :id")
    fun getEmployeeById(id: Int): Flow<Employee>
}