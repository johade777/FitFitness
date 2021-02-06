package com.example.fitfitness.viewmodel.activitymodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.fitfitness.data.Attempt
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.room.repositories.ExerciseRepository

class ExerciseActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var exerciseRepository = ExerciseRepository(application.applicationContext)
    var mExerciseLiveData: LiveData<List<Exercise>> = exerciseRepository.getAllExercises()

    fun getExerciseWithAttempts(exerciseId: Long) : LiveData<List<Attempt>> {
       return exerciseRepository.getAttemptsByExerciseId(exerciseId)
    }
}