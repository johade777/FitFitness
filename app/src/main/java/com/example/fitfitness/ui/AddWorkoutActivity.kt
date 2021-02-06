package com.example.fitfitness.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fitfitness.R
import com.example.fitfitness.data.Workout
import com.example.fitfitness.ui.fragments.WorkoutAddExercisesFragment
import com.example.fitfitness.ui.fragments.WorkoutDayFragment
import com.example.fitfitness.ui.fragments.WorkoutNameFragment
import com.example.fitfitness.viewmodel.activitymodels.AddWorkoutViewModel
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_add_workout.*

class AddWorkoutActivity : AppCompatActivity() {
    private lateinit var viewModel: AddWorkoutViewModel
    private lateinit var loadingHud: KProgressHUD
    private val workoutNameFragment: WorkoutNameFragment = WorkoutNameFragment.newInstance()
    private val workoutAddExercisesFragment : WorkoutAddExercisesFragment = WorkoutAddExercisesFragment()
    private val workoutDayFragment : WorkoutDayFragment = WorkoutDayFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_workout)

        loadingHud = KProgressHUD(this)
        loadingHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(AddWorkoutViewModel::class.java)

        viewModel.mAllExercisesLiveData.observe(this, {
            workoutAddExercisesFragment.setExerciseList(viewModel.exerciseNames(it))
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
            when {
                workoutNameFragment.isVisible -> {
                    workoutNameFragment.setWorkoutName()
                    supportFragmentManager!!.beginTransaction().replace(R.id.workout_Frag_Container, workoutAddExercisesFragment,"FragmentTwo").commit()
                }
                workoutAddExercisesFragment.isVisible -> {
                    supportFragmentManager!!.beginTransaction().replace(R.id.workout_Frag_Container, workoutDayFragment,"FragmentThree").commit()
                }
                else -> {
                    val workoutName = viewModel.workoutName
                    if(workoutName.isNotEmpty() && viewModel.selectedDay != -1) {
                        val workout = Workout(workoutName, viewModel.selectedDay)
                        viewModel.saveWorkout(workout)
                    }
                }
            }
        }
//
//        cancel_button.setOnClickListener {
//            Toast.makeText(this, "Cancel Toast", Toast.LENGTH_LONG).show()
//        }
    }
}