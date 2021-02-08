package com.example.fitfitness.viewmodel.activitymodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.relationships.WorkoutsWithExercises
import com.example.fitfitness.room.FitDatabase
import com.example.fitfitness.room.repositories.WorkoutRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WorkoutActivityViewModel(application: Application) : AndroidViewModel(application){
    private val db: FitDatabase = FitDatabase.getInstance(application.applicationContext)!!
//    var workoutRepository: WorkoutRepository = WorkoutRepository(application.applicationContext)
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var loadingString: MutableLiveData<String> = MutableLiveData("")
    var mWorkoutWithExerciseLiveData: MutableLiveData<WorkoutsWithExercises> = MutableLiveData()
    var workoutVolumeLiveData: MutableLiveData<Float> = MutableLiveData()

    //TODO This is dangerous as return could be null if DB fails
    fun getWorkoutsWithExercises(workoutId: Long): LiveData<WorkoutsWithExercises> {
        isLoading.value = true
        loadingString.value = "Loading Workout"
        viewModelScope.launch {
            mWorkoutWithExerciseLiveData.value = db.workoutExercisesDao().getWorkoutWithExercises(workoutId)
            calculateWorkoutVolume(mWorkoutWithExerciseLiveData.value!!.exercises)
            delay(1000)
            isLoading.value = false
        }
        return mWorkoutWithExerciseLiveData
    }

    private fun calculateWorkoutVolume(exercises: List<Exercise>){
        var volume = 0.0f;
        for(exercise in exercises){
            volume += (exercise.currentWeight * exercise.reps * exercise.sets)
        }
        workoutVolumeLiveData.value = volume
    }

    fun getWorkoutVolumeLiveData() : LiveData<Float>{
        return this.workoutVolumeLiveData
    }

    fun deleteExercise(exerciseId: Long, workoutId: Long) {
        viewModelScope.launch {
            loadingString.value = "Removing Exercise"
            isLoading.value = true;
            db.workoutExercisesDao().delete(exerciseId, workoutId)
            delay(1000)
            isLoading.value = false
        }
    }
}