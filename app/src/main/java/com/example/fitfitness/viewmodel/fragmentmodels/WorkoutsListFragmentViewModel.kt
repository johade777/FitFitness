package com.example.fitfitness.viewmodel.fragmentmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitfitness.data.Workout
import com.example.fitfitness.room.repositories.WorkoutRepository

class WorkoutsListFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private var workoutRepository = WorkoutRepository(application.applicationContext)
    var mWorkoutLiveData: LiveData<List<Workout>>

    private var _text = MutableLiveData<String>().apply {
        value = "This is Workouts Fragment"
    }
//    val mWorkoutLiveData = MutableLiveData<MutableList<Workout>>()
    val text: LiveData<String> = _text

    init {
        mWorkoutLiveData = workoutRepository.getAllWorkouts()
    }

//    private fun addWorkout(workout: Workout) {
//        mWorkoutLiveData.value?.add(workout)
//        mWorkoutLiveData.notifyObserver()
//    }
}