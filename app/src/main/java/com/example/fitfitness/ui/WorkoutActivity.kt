package com.example.fitfitness.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitfitness.R
import com.example.fitfitness.adapters.ExerciseAdapter
import com.example.fitfitness.adapters.OnItemClickListener
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Workout
import com.example.fitfitness.room.FitDatabase
import com.example.fitfitness.viewmodel.activitymodels.ExerciseActivityViewModel
import com.example.fitfitness.viewmodel.activitymodels.WorkoutActivityViewModel
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_workout_list.*
import kotlin.collections.ArrayList


class WorkoutActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var workoutViewModel: WorkoutActivityViewModel
    private lateinit var loadingHud: KProgressHUD
    private var exercises: List<Exercise> = ArrayList()
    private val adapter = ExerciseAdapter(exercises, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_list)

        loadingHud = KProgressHUD(this)
        loadingHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)

        val workout = intent.getSerializableExtra("workout") as? Workout
        workoutViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            WorkoutActivityViewModel::class.java
        )

        workoutViewModel.getWorkoutsWithExercises(workout!!.workoutId).observe(this, {
            exercises = it.exercises
            adapter.setNewExercises(it.exercises)
        })

        workoutViewModel.getWorkoutVolumeLiveData().observe(this,{
            workoutVolume.text = "$it lb"
        })

        linearLayoutManager = LinearLayoutManager(this)
        exercise_list.layoutManager = linearLayoutManager
        exercise_list.adapter = adapter

        workoutName.text = workout?.name
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, ExerciseActivity::class.java)
        intent.putExtra("exercise", exercises[position])
        startActivity(intent)
    }
}
