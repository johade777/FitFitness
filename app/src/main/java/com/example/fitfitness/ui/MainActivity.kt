package com.example.fitfitness.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.fitfitness.R
import com.example.fitfitness.data.*
import com.example.fitfitness.room.FitDatabase
import com.example.fitfitness.room.dao.relationshipDao.WorkoutExercisesDao
import com.example.fitfitness.room.repositories.ExerciseRepository
import java.time.OffsetDateTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db: FitDatabase = FitDatabase.getInstance(this)!!
//        val thread = Thread {
//            db.exerciseDao().insert(Exercise(1,"Curl", "Bicep", 25.0f, 10, 3))
//            db.exerciseDao().insert(Exercise(2,"Bench Press", "Pectoral", 140.0f, 5, 4))
//            db.exerciseDao().insert(Exercise(3,"Dead Lift", "Hamstring", 280.0f, 5, 5));
//
//            db.attemptDao().insert(Attempt(1, OffsetDateTime.now(),false, 25.0f, 1))
//            db.attemptDao().insert(Attempt(2, OffsetDateTime.now(),false, 20.0f, 1))
//            db.attemptDao().insert(Attempt(3, OffsetDateTime.now(),false, 15.0f, 1))
//
//            db.workoutDao().insert(Workout(1,"Legs + Heavy Bicep", 1))
//            db.workoutDao().insert(Workout(2,"Chest + Light Triceps", 3))
//            db.workoutDao().insert(Workout(3,"Back + Light Bicep",4))
//            db.workoutDao().insert(Workout(4,"Shoulders + Heavy Triceps", 5))
//            db.workoutDao().insert(Workout(5,"Misc",6))
//
//            db.routineDao().insert(Routine(1, "Bro Splits"))
//
//            db.workoutExercisesDao().insert(WorkoutSession(1, 1))
//            db.workoutExercisesDao().insert(WorkoutSession(1, 2))
//            db.workoutExercisesDao().insert(WorkoutSession(1, 3))
//
//            db.routineWorkoutDao().insert(RoutineSession(1, 1))
//            db.routineWorkoutDao().insert(RoutineSession(1, 2))
//            db.routineWorkoutDao().insert(RoutineSession(1, 3))
//            db.routineWorkoutDao().insert(RoutineSession(1, 4))
//            db.routineWorkoutDao().insert(RoutineSession(1, 5))
//
////            var temp = db.attemptDao().getAllAttempts()
//
////            var temp = db.exerciseDao().getAllExercises()
////            var tempVal = temp.value
////            var text = "wait";
//
//        }
//        thread.start()

//        var temp = db.exerciseDao().getAllExercises()
//        var temp = db.routineDao().getAllRoutines()
//        var temp = db.workoutExercisesDao().getWorkoutWithExercises()
//        var temp = db.routineWorkoutDao().getRoutinesWithWorkouts()
//
//        temp.observe(this, Observer {
//            var test = it
//            var wait = "wait"
//        })

        var exerciseRepository = ExerciseRepository(this)

        var temp = exerciseRepository.getAllExercises()

        temp.observe(this, Observer {
            var test = it
            var wait = "wait"
        })

        var text = "wait";
    }
}
