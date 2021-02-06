package com.example.fitfitness.viewmodel.fragmentmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.room.repositories.ExerciseRepository

class ExerciseListFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private var exerciseRepository = ExerciseRepository(application.applicationContext)
    var mExerciseLiveData: LiveData<List<Exercise>> = exerciseRepository.getAllExercises()

    private var _text = MutableLiveData<String>().apply {
        value = "This is Exercises Fragment"
    }
//    val mExerciseLiveData = MutableLiveData<MutableList<Exercise>>()
    val text: LiveData<String> = _text

    //    private fun addExercise(exercise: Exercise) {
//        mExerciseLiveData.value?.add(exercise)
//        mExerciseLiveData.notifyObserver()
//    }
}