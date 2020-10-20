package com.example.fitfitness.data.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.fitfitness.data.Routine
import com.example.fitfitness.data.RoutineSession
import com.example.fitfitness.data.Workout

data class RoutinesWithWorkouts(
    @Embedded val routine: Routine,
    @Relation(
        parentColumn = "routineId",
        entityColumn = "workoutId",
        associateBy = Junction(RoutineSession::class)
    )
    val workouts: List<Workout>
) {
}