package com.example.fitfitness.viewmodel.activitymodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitfitness.data.Workout
import com.example.fitfitness.room.repositories.WorkoutRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddWorkoutViewModel(application: Application) : AndroidViewModel(application) {
    private var workoutRepository = WorkoutRepository(application.applicationContext)
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var loadingString: MutableLiveData<String> = MutableLiveData("")
    var successfullyAdded: Boolean = false

    fun saveWorkout(workout: Workout){
        isLoading.value = true
        loadingString.value = "Adding Workout"
        viewModelScope.launch {

            //TODO Needs a way to know if successfully added to database, will currently always return true
            successfullyAdded = (workoutRepository.insert(workout) != null)
            delay(1000)
            loadingString.value = "Workout Added"
            delay(500)
            isLoading.value = false
        }
    }
}