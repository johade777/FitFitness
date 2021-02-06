package com.example.fitfitness.room.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Workout
import com.example.fitfitness.data.relationships.WorkoutsWithExercises
import com.example.fitfitness.room.FitDatabase
import com.example.fitfitness.room.dao.WorkoutDao
import com.example.fitfitness.room.dao.relationshipDao.ExerciseAttemptDao
import com.example.fitfitness.room.dao.relationshipDao.WorkoutExercisesDao

class WorkoutRepository(context: Context) : BaseRepository() {

    private lateinit var workoutDao: WorkoutDao
    private lateinit var workoutExercisesDao: WorkoutExercisesDao
    private lateinit var allWorkouts: LiveData<List<Workout>>

    init {
        FitDatabase.getInstance(context)?.let {
            workoutDao = it.workoutDao()
            workoutExercisesDao = it.workoutExercisesDao()
            allWorkouts = workoutDao.getAllWorkouts()
        }
    }

//    fun insert(workout: Workout): Long {
////        DoInBackgroundAsync<Workout> {
////            workoutDao.insert(workout)
////        }.execute()
//
//        return workoutDao.insert(workout)
//    }

    suspend fun getWorkoutWithExercises(workoutId: Long): WorkoutsWithExercises  {
        return workoutExercisesDao.getWorkoutWithExercises(workoutId)
    }

    suspend fun insert(workout: Workout): Long  {
        return workoutDao.insert(workout)
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