package com.example.fitfitness.room.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.fitfitness.data.Attempt
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.relationships.ExerciseWithAttempts
import com.example.fitfitness.room.FitDatabase
import com.example.fitfitness.room.dao.ExerciseDao
import com.example.fitfitness.room.dao.relationshipDao.ExerciseAttemptDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

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

    suspend fun insert(exercise: Exercise): Long {
        return exerciseDao.insert(exercise)
    }

    fun getAttemptsByExerciseId(exerciseId: Long): LiveData<List<Attempt>>{
        return exerciseAttemptDao.getAttemptsByExerciseId(exerciseId)
    }

    fun getAllExercises(): LiveData<List<Exercise>> {
        return allExercises
    }

    suspend fun updateExercises(exercise: Exercise) {
        exerciseDao.update(exercise)
    }

    suspend fun removeExercises(exercise: Exercise) {
        exerciseDao.remove(exercise)
    }
}