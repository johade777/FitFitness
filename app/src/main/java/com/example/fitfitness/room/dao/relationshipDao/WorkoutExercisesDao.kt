package com.example.fitfitness.room.dao.relationshipDao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fitfitness.data.Attempt
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.WorkoutSession
import com.example.fitfitness.data.relationships.WorkoutsWithExercises

@Dao
interface WorkoutExercisesDao {

    @Insert
    suspend fun insert(session: WorkoutSession)

    @Query("Delete FROM workout_sessions where exerciseId == :deleteExerciseId AND workoutId == :deleteWorkoutId")
    suspend fun delete(deleteExerciseId: Long, deleteWorkoutId: Long)

    @Transaction
    @Query("SELECT * FROM workouts WHERE workoutId == :myWorkoutId")
    suspend fun getWorkoutWithExercises(myWorkoutId: Long): WorkoutsWithExercises
}