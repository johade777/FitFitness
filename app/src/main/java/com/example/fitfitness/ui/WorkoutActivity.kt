package com.example.fitfitness.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitfitness.R
import com.example.fitfitness.adapters.ExerciseAdapter
import com.example.fitfitness.adapters.OnItemClickListener
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Workout
import kotlinx.android.synthetic.main.activity_workout_list.*
import kotlin.collections.ArrayList


class WorkoutActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private var exercises: List<Exercise> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_list)

        val workout = intent.getSerializableExtra("workout") as? Workout
//        exercises = workout!!.exercises

        linearLayoutManager = LinearLayoutManager(this)
        exercise_list.layoutManager = linearLayoutManager
        exercise_list.adapter = ExerciseAdapter(exercises, this)

        workoutName.text = workout?.name
        workoutVolume.text = getWorkoutVolume().toString() + " lb"
    }

    private fun getWorkoutVolume(): Int {
        var volume: Int = 0

        for(exercise in exercises){
            volume += exercise.getExerciseVolume()
        }

        return volume
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, ExerciseActivity::class.java)
        intent.putExtra("exercise", exercises[position])
        startActivity(intent)
    }
}
