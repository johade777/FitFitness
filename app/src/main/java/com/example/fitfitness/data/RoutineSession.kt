package com.example.fitfitness.data

import androidx.room.Entity

@Entity(tableName = "routine_sessions", primaryKeys = ["routineId", "workoutId"])
data class RoutineSession(
    val routineId: Long,
    val workoutId: Long)