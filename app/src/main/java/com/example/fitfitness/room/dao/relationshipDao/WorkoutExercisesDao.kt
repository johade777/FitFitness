package com.example.fitfitness.room.dao.relationshipDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.fitfitness.data.Attempt
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.WorkoutSession
import com.example.fitfitness.data.relationships.WorkoutsWithExercises

@Dao
interface WorkoutExercisesDao {

    @Insert
    fun insert(session: WorkoutSession)

    @Transaction
    @Query("SELECT * FROM workouts")
    fun getWorkoutWithExercises(): LiveData<List<WorkoutsWithExercises>>
}