package com.example.fitfitness.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true) val workoutId: Long = 0,
    val name: String,
    val day: Int) : Serializable {


}