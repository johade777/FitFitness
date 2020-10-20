package com.example.fitfitness.room.dao.relationshipDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.fitfitness.data.RoutineSession
import com.example.fitfitness.data.relationships.RoutinesWithWorkouts

@Dao
interface RoutineWorkoutsDao {

    @Insert
    fun insert(session: RoutineSession)

    @Transaction
    @Query("SELECT * FROM routines")
    fun getRoutinesWithWorkouts(): LiveData<List<RoutinesWithWorkouts>>
}