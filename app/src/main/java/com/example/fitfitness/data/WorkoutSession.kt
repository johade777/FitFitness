package com.example.fitfitness.data

import androidx.room.Entity

@Entity(tableName = "workout_sessions", primaryKeys = ["exerciseId", "workoutId"])
data class WorkoutSession(
    val workoutId: Long,
    val exerciseId: Long
)