package com.example.fitfitness.viewmodel.activitymodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Workout
import com.example.fitfitness.data.WorkoutSession
import com.example.fitfitness.room.FitDatabase
import com.example.fitfitness.room.repositories.ExerciseRepository
import com.example.fitfitness.room.repositories.WorkoutRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class AddWorkoutViewModel(application: Application) : AndroidViewModel(application) {
    val db: FitDatabase = FitDatabase.getInstance(application.applicationContext)!!
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var loadingString: MutableLiveData<String> = MutableLiveData("")
    var successfullyAdded: Boolean = false
    var mAllExercisesLiveData: LiveData<List<Exercise>> = db.exerciseDao().getAllExercises()
    private var mSelectedExercisesLiveData: MutableLiveData<List<Exercise>> = MutableLiveData()
    private var selectedExercises: MutableList<Exercise> = mutableListOf()
    var exerciseList: List<Exercise> = ArrayList()

    var workoutName: String = ""
    var selectedDay: Int = -1

    fun saveWorkout(workout: Workout){
        isLoading.value = true
        loadingString.value = "Creating Workout"
        viewModelScope.launch {
            //TODO Needs a way to know if successfully added to database, will currently always return true
            var workoutId = db.workoutDao().insert(workout)
            successfullyAdded = (workoutId != null)
            delay(1000)

            for (exercise in selectedExercises){
                var workoutSession = WorkoutSession(workoutId, exercise.exerciseId)
                loadingString.value = "Adding " + exercise.name
                db.workoutExercisesDao().insert(workoutSession)
                delay(100)
            }
            loadingString.value = "Workout Created"
            delay(500)
            isLoading.value = false
        }
    }

    fun exerciseNames(exerciseList: List<Exercise>): List<String>{
        var nameList = mutableListOf<String>()
        this.exerciseList = exerciseList
        for (exercise in exerciseList){
            nameList.add(exercise.name)
        }
        return nameList
    }

    fun addExercise(exerciseName: String){
        for (exercise in exerciseList){
            if (exerciseName.equals(exercise.name, ignoreCase = true)){
                selectedExercises.add(exercise)
                mSelectedExercisesLiveData.value = selectedExercises
                return
            }
        }
    }

    fun getSelectedExercisesLiveData() : LiveData<List<Exercise>>{
        return mSelectedExercisesLiveData
    }
}