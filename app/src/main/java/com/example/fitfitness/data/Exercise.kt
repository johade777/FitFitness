package com.example.fitfitness.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import kotlin.math.roundToInt

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Long,
    val name: String,
    val primaryMuscle: String,
    val currentWeight: Float,
    val reps: Int,
    val sets: Int) : Serializable {

    constructor(name: String, primaryMuscle: String, currentWeight: Float, reps: Int, sets: Int) : this(0, name, primaryMuscle, currentWeight, reps, sets)

    fun getExerciseVolume(): Int {
        return (currentWeight * reps * sets).roundToInt()
    }
}