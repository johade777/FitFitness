package com.example.fitfitness.room.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.fitfitness.data.Workout
import com.example.fitfitness.room.FitDatabase
import com.example.fitfitness.room.dao.WorkoutDao

class WorkoutRepository(context: Context) : BaseRepository() {

    private lateinit var workoutDao: WorkoutDao
    private lateinit var allWorkouts: LiveData<List<Workout>>

    init {
        FitDatabase.getInstance(context)?.let {
            workoutDao = it.workoutDao()
            allWorkouts = workoutDao.getAllWorkouts()
        }
    }

    fun insert(workout: Workout) {
        DoInBackgroundAsync<Workout> {
            workoutDao.insert(workout)
        }.execute()
    }

    fun getAllWorkouts(): LiveData<List<Workout>> {
        return allWorkouts
    }

    fun updateWorkout(workout: Workout) {
        DoInBackgroundAsync<Workout> {
            workoutDao.update(workout)
        }.execute()
    }

    fun removeExercise(workout: Workout) {
        DoInBackgroundAsync<Workout> {
            workoutDao.remove(workout)
        }.execute()
    }
}