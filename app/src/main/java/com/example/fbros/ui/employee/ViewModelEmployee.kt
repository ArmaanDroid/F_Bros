package com.example.fbros.ui.employee

import android.view.View
import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fbros.db.dao.EmployeeDao
import com.example.fbros.model.Employee
import kotlinx.coroutines.launch

class ViewModelEmployee(val dao: EmployeeDao) : ViewModel() {

    val employeeList: LiveData<List<Employee>> = dao.getEmployees().asLiveData()

    fun addEmployee(name: String, email: String, phone: String, address: String, gender: Boolean) {
        val employee = Employee(
            name = name,
            emailId = email,
            phoneNumber = phone,
            address = address,
            gender = gender
        )
        viewModelScope.launch { dao.insert(employee) }
    }

    fun getEmployeeById(id: Int): LiveData<Employee> {
        return dao.getEmployeeById(id).asLiveData()
    }

}

class EmployeeViewModelFactory(private val dao: EmployeeDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelEmployee::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelEmployee(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}