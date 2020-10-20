package com.example.fitfitness.room.dao.relationshipDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.fitfitness.data.Attempt
import com.example.fitfitness.data.relationships.ExerciseWithAttempts

@Dao
interface ExerciseAttemptDao {

    @Transaction
    @Query("SELECT * FROM exercises, attempts WHERE exercises.exerciseId ==  attempts.exerciseId")
    fun getExerciseWithAttempts(): LiveData<List<ExerciseWithAttempts>>

    @Transaction
    @Query("SELECT * FROM exercises")
    fun getAllExercisesWithAttempts(): LiveData<List<ExerciseWithAttempts>>

    @Transaction
    @Query("SELECT * FROM attempts WHERE :myExerciseId ==  attempts.exerciseId")
    fun getAttemptsByExerciseId(myExerciseId: Long): LiveData<List<Attempt>>

}