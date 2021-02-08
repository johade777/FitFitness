package com.example.fitfitness.viewmodel.fragmentmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitfitness.data.Workout
import com.example.fitfitness.room.FitDatabase
import kotlinx.coroutines.launch

class WorkoutsListFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val db: FitDatabase = FitDatabase.getInstance(application.applicationContext)!!
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var loadingString: MutableLiveData<String> = MutableLiveData("This is Workouts Fragment")
    var mWorkoutLiveData: LiveData<List<Workout>> = db.workoutDao().getAllWorkouts()

    private var _text = MutableLiveData<String>().apply {
        value = "This is Workouts Fragment"
    }

    val text: LiveData<String> = _text

    fun deleteExercise(exerciseId: Long, workoutId: Long) {
        viewModelScope.launch {
            loadingString.value = "Removing Exercise"
            isLoading.value = true
            db.workoutExercisesDao().deleteWorkoutSessionExercise(exerciseId, workoutId)
            isLoading.value = false
        }
    }

    fun deleteWorkout(workoutId: Long) {
        viewModelScope.launch {
            loadingString.value = "Deleting Workout"
            isLoading.value = true
            db.workoutExercisesDao().deleteWorkoutSessions(workoutId)
            db.workoutDao().deleteWorkout(workoutId)
        }
    }
}