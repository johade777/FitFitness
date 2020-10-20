package com.example.fitfitness.data.prepopulatedData

import com.example.fitfitness.data.Exercise

class ExerciseData {

     fun populateExerciseData(): List<Exercise> {
        return listOf(
            Exercise(1,"Curl", "Bicep", 25.0f, 10, 3),
            Exercise(2,"Bench Press", "Pectoral", 140.0f, 5, 4),
            Exercise(3,"Dead Lift", "Hamstring", 280.0f, 5, 5)
        )
    }
}