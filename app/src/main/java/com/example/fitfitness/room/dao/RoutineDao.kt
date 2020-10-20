package com.example.fitfitness.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fitfitness.data.Routine

@Dao
interface RoutineDao {

    @Insert
    fun insert(routine: Routine)

    @Delete
    fun remove(routine: Routine)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(routine: Routine): Int

    @Query("SELECT * FROM routines")
    fun getAllRoutines(): LiveData<List<Routine>>
}