package com.example.fbros.ui.stop

import android.view.View
import androidx.lifecycle.*
import com.example.fbros.db.dao.StopDao
import com.example.fbros.model.Stop
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ViewModelStop(val dao:StopDao): ViewModel() {

    val stopList: LiveData<List<Stop>> = dao.getAllStops().asLiveData()

    fun getStopById(stopId: Int): LiveData<Stop> {
        return dao.getStopById(stopId).asLiveData()
    }

    fun addStop(name: String, city: String) {
        viewModelScope.launch {
//            dao.insert(Stop(name = name, color = city))
        }
    }

    fun updateRoute(id: Int, name: String, city: String) {

        viewModelScope.launch {
//            dao.update(Stop(id = id, name, city))
        }

    }

    fun deleteRoute(stop : Stop) {
        viewModelScope.launch {
            dao.delete(stop)
        }
    }

    fun validateEntries(name: String, city: String): Boolean {
        if (name.isBlank() or city.isBlank()) return false
        return true
    }


}

class StopViewModelFactory(val dao: StopDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewModelStop::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ViewModelStop(dao) as T
        }
        throw IllegalArgumentException("Unknown view model class")

    }

}