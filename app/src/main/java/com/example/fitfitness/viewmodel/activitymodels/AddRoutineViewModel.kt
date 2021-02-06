package com.example.fitfitness.viewmodel.activitymodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitfitness.data.Routine
import com.example.fitfitness.room.repositories.RoutineRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddRoutineViewModel(application: Application) : AndroidViewModel(application) {
    private var routineRepository = RoutineRepository(application.applicationContext)
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var loadingString: MutableLiveData<String> = MutableLiveData("")
    var successfullyAdded: Boolean = false

    fun saveRoutine(routine: Routine){
        isLoading.value = true
        loadingString.value = "Adding Workout"
        viewModelScope.launch {

            //TODO Needs a way to know if successfully added to database, will currently always return true
            successfullyAdded = (routineRepository.insert(routine) != null)
            delay(1000)
            loadingString.value = "Workout Added"
            delay(500)
            isLoading.value = false
        }
    }
}