package com.example.fitfitness.viewmodel.activitymodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.room.repositories.ExerciseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddExerciseViewModel(application: Application) : AndroidViewModel(application) {
    private var exerciseRepository = ExerciseRepository(application.applicationContext)
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var loadingString: MutableLiveData<String> = MutableLiveData("")
    var successfullyAdded: Boolean = false

    fun saveExercise(exercise: Exercise){
        isLoading.value = true
        loadingString.value = "Adding Exercise"
        viewModelScope.launch {

            //TODO Needs a way to know if successfully added to database, will currently always return true
            successfullyAdded = (exerciseRepository.insert(exercise) != null)
            delay(1000)
            loadingString.value = "Exercise Added"
            delay(500)
            isLoading.value = false
        }
    }

    fun saveExerciseToWorkout(workout: Long, exercise: Exercise) {

    }
}