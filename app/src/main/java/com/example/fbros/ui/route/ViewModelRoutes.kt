package com.example.fbros.ui.route

import androidx.lifecycle.*
import com.example.fbros.db.dao.RouteDao
import com.example.fbros.model.Route
import kotlinx.coroutines.launch

class ViewModelRoutes(private val dao: RouteDao) : ViewModel() {
    val routeList: LiveData<List<Route>> = dao.getAllRoutes().asLiveData()

    fun getRouteById(routeId: Int): LiveData<Route> {
        return dao.getRouteById(routeId).asLiveData()
    }

    fun addRoute(name: String, city: String) {
        viewModelScope.launch {
            dao.insert(Route(name = name, city = city))
        }
    }

    fun updateRoute(routeId: Int, name: String, city: String) {

        viewModelScope.launch {
            dao.update(Route(id = routeId, name, city))
        }

    }

    fun deleteRoute(currentRoute: Route) {
        viewModelScope.launch {
            dao.delete(currentRoute)
        }
    }

    fun validateEntries(name: String, city: String): Boolean {
        if (name.isBlank() or city.isBlank()) return false
        return true
    }



}

class RoutesViewModelFactory(private val dao: RouteDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelRoutes::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelRoutes(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}