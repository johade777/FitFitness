package com.example.fitfitness.data.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fitfitness.data.Attempt
import com.example.fitfitness.data.Exercise

data class ExerciseWithAttempts(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "attemptId"
    )

    val attempts: List<Attempt>
)