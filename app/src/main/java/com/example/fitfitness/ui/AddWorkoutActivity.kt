package com.example.fitfitness.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fitfitness.R
import com.example.fitfitness.ui.fragments.WorkoutAddExercisesFragment
import com.example.fitfitness.ui.fragments.WorkoutDayFragment
import com.example.fitfitness.ui.fragments.WorkoutNameFragment
import com.example.fitfitness.viewmodel.activitymodels.AddWorkoutViewModel
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_add_workout.*

class AddWorkoutActivity : AppCompatActivity() {
    private lateinit var viewModel: AddWorkoutViewModel
    private lateinit var loadingHud: KProgressHUD
    private var exerciseList: List<String> = ArrayList()
    private val workoutNameFragment: WorkoutNameFragment = WorkoutNameFragment()
    private val workoutAddExercisesFragment : WorkoutAddExercisesFragment = WorkoutAddExercisesFragment()
    private val workoutDayFragment : WorkoutDayFragment = WorkoutDayFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_workout)

        loadingHud = KProgressHUD(this)
        loadingHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)

        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            AddWorkoutViewModel::class.java
        )

        viewModel.mExerciseLiveData.observe(this, Observer {
            exerciseList = viewModel.exerciseNames(it)
            workoutAddExercisesFragment.setExerciseList(exerciseList)
        })

        supportFragmentManager!!.beginTransaction().add(R.id.workout_Frag_Container, workoutNameFragment,"FragmentOne").commit()

        viewModel.isLoading.observe(this, {
            if(it){
                loadingHud.show()
            }else{
                if(viewModel.successfullyAdded){
                    loadingHud.dismiss()
                    finish()
                }
            }
        })

        viewModel.loadingString.observe(this, {
            loadingHud.setLabel(it)
        })

        next_button.setOnClickListener {
//            val workoutName = workout_Name_Input.text.toString()
//            if(workoutName.isNotEmpty() && selectedDay != -1){
//                val workout = Workout(workoutName, selectedDay)
//                viewModel.saveWorkout(workout)
//            }

            when {
                workoutNameFragment.isVisible -> {
                    supportFragmentManager!!.beginTransaction().replace(R.id.workout_Frag_Container, workoutAddExercisesFragment,"FragmentTwo").commit()
                }
                workoutAddExercisesFragment.isVisible -> {
                    supportFragmentManager!!.beginTransaction().replace(R.id.workout_Frag_Container, workoutDayFragment,"FragmentThree").commit()
                }
                else -> {

                }
            }

        }
//
//        cancel_button.setOnClickListener {
//            Toast.makeText(this, "Cancel Toast", Toast.LENGTH_LONG).show()
//        }
    }
}