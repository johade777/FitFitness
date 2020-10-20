package com.example.fitfitness.room.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.fitfitness.data.Attempt
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.relationships.ExerciseWithAttempts
import com.example.fitfitness.room.FitDatabase
import com.example.fitfitness.room.dao.ExerciseDao
import com.example.fitfitness.room.dao.relationshipDao.ExerciseAttemptDao

class ExerciseRepository(context: Context) : BaseRepository() {

    private lateinit var exerciseDao: ExerciseDao
    private lateinit var exerciseAttemptDao: ExerciseAttemptDao
    private lateinit var allExercises: LiveData<List<Exercise>>

    init {
        FitDatabase.getInstance(context)?.let {
            exerciseDao = it.exerciseDao()
            exerciseAttemptDao = it.exerciseAttemptDao()
            allExercises = exerciseDao.getAllExercises()
        }
    }

    fun insert(exercise: Exercise) {
        DoInBackgroundAsync<Exercise> {
            exerciseDao.insert(exercise)
        }.execute()
    }

    fun getAttemptsByExerciseId(exerciseId: Long): LiveData<List<Attempt>>{
        return exerciseAttemptDao.getAttemptsByExerciseId(exerciseId)
    }

    fun getAllExercises(): LiveData<List<Exercise>> {
        return allExercises
    }

    fun updateExercises(exercise: Exercise) {
        DoInBackgroundAsync<Exercise> {
            exerciseDao.update(exercise)
        }.execute()
    }

    fun removeExercises(exercise: Exercise) {
        DoInBackgroundAsync<Exercise> {
            exerciseDao.remove(exercise)
        }.execute()
    }
}