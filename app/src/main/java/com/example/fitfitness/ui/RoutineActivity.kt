package com.example.fitfitness.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitfitness.R
import com.example.fitfitness.adapters.OnItemClickListener
import com.example.fitfitness.adapters.WorkoutAdapter
import com.example.fitfitness.data.Attempt
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Workout
import kotlinx.android.synthetic.main.activity_routine.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RoutineActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var workouts: ArrayList<Workout> = ArrayList()
    private var exercises: ArrayList<Exercise> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine)

        val parser = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
        val date1: Date = parser.parse("09/20/2020")
        val date2: Date = parser.parse("09/21/2020")
        val date3: Date = parser.parse("09/22/2020")

//        val attempt1 = Attempt(date1, false, 25.0f)
//        val attempt2 = Attempt(date2, true, 20.0f)
//        val attempt3 = Attempt(date3, true, 15.0f)
//
//        val attempts = listOf(attempt1, attempt2, attempt3)
//        exercises.add(Exercise("Curl", "Bicep", 25.0f, 10, 3, attempts))
//        exercises.add(Exercise("Bench Press", "Pectoral", 140.0f, 5, 4, attempts))
//        exercises.add(Exercise("Dead Lift", "Hamstring", 280.0f, 5, 5, attempts))
//
//        var list: List<Exercise> = emptyList()
//
//        val workout1 = Workout("Legs + Heavy Bicep", exercises, 1)
//        val workout2 = Workout("Chest + Light Triceps", exercises, 3)
//        val workout3 = Workout("Back + Light Bicep", exercises, 4)
//        val workout4 = Workout("Shoulders + Heavy Triceps", exercises,5)
//        val workout5 = Workout("Misc", list,6)
//
//        workouts.add(workout1)
//        workouts.add(workout2)
//        workouts.add(workout3)
//        workouts.add(workout4)
//        workouts.add(workout5)
//        linearLayoutManager = LinearLayoutManager(this)
//        workout_list.layoutManager = linearLayoutManager
//        workout_list.adapter = WorkoutAdapter(workouts, this);
//
//        routineName.text = "Bro Splits"
//        routineVolume.text = getRoutineVolume().toString() + " lb"
    }

//    private fun getRoutineVolume(): Int {
//        var volume: Int = 0
//
//        for (workout in workouts){
//            volume += workout.getWorkoutVolume()
//        }
//
//        return volume
//    }

    override fun onItemClick(position: Int) {
//        Toast.makeText(this, exercises[position].name, Toast.LENGTH_LONG).show()
        val intent = Intent(this, WorkoutActivity::class.java)
        intent.putExtra("workout", workouts[0])
        startActivity(intent)
    }
}