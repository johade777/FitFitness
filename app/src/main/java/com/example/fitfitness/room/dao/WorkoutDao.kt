package com.example.fitfitness.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Routine
import com.example.fitfitness.data.Workout

@Dao
interface WorkoutDao {
    @Insert
    fun insert(workout: Workout)

    @Delete
    fun remove(workout: Workout)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(workout: Workout): Int

    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): LiveData<List<Workout>>
}