package com.example.fitfitness.data.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Workout
import com.example.fitfitness.data.WorkoutSession

data class WorkoutsWithExercises(
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "workoutId",
        entityColumn = "exerciseId",
        associateBy = Junction(WorkoutSession::class)
    )
    val exercises: List<Exercise>
) {
    fun getWorkoutVolume(): Int {
        var volume: Int = 0;

//        for(exercise in exercises.value){
//            volume += exercise.getExerciseVolume()
//        }
        return volume
    }
}