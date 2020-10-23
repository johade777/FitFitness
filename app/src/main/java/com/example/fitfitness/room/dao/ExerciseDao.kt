package com.example.fitfitness.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fitfitness.data.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(exercise: Exercise) : Long

    @Delete
    suspend fun remove(exercise: Exercise)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(exercise: Exercise): Int

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): LiveData<List<Exercise>>
}