package com.example.fitfitness.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fitfitness.data.Exercise

@Dao
interface ExerciseDao {
    @Insert
    fun insert(exercise: Exercise)

    @Delete
    fun remove(exercise: Exercise)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(exercise: Exercise): Int

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): LiveData<List<Exercise>>
}