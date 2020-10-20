package com.example.fitfitness.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fitfitness.data.Attempt
import com.example.fitfitness.data.Routine

@Dao
interface AttemptDao {
    @Insert
    fun insert(attempt: Attempt)

    @Delete
    fun remove(attempt: Attempt)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(attempt: Attempt): Int

    @Query("SELECT * FROM attempts ")
    fun getAllAttempts(): LiveData<List<Attempt>>
}