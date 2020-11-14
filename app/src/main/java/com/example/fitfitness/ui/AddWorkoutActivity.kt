package com.example.fitfitness.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.get
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import com.example.fitfitness.R
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Workout
import com.example.fitfitness.viewmodel.activitymodels.AddExerciseViewModel
import com.example.fitfitness.viewmodel.activitymodels.AddWorkoutViewModel
import com.google.android.material.button.MaterialButton
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_add_exercise.*
import kotlinx.android.synthetic.main.activity_add_exercise.cancel_button
import kotlinx.android.synthetic.main.activity_add_exercise.submit_button
import kotlinx.android.synthetic.main.activity_add_workout.*
import kotlinx.android.synthetic.main.view_weekday_picker.*

class AddWorkoutActivity : AppCompatActivity() {
    private lateinit var viewModel: AddWorkoutViewModel
    private lateinit var loadingHud: KProgressHUD
    private var selectedDay: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_workout)

        loadingHud = KProgressHUD(this)
        loadingHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)

        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            AddWorkoutViewModel::class.java
        )

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

        toggle_button_group.addOnButtonCheckedListener {group, checkedId, isChecked ->
            if(group.checkedButtonId == -1) {
                group.check(checkedId)
            }

            for(buttonIndex in 0 until toggle_button_group.childCount){
                val child = toggle_button_group.getChildAt(buttonIndex) as MaterialButton
                if(child.isChecked){
                    child.setBackgroundColor(Color.GREEN)
                    selectedDay = buttonIndex + 1
                }else{
                    child.setBackgroundColor(Color.RED)
                }
            }
        }

        submit_button.setOnClickListener {
            val workoutName = workout_Name_Input.text.toString()
            if(workoutName.isNotEmpty() && selectedDay != -1){
                val workout = Workout(workoutName, selectedDay)
                viewModel.saveWorkout(workout)
            }
        }

        cancel_button.setOnClickListener {
            Toast.makeText(this, "Cancel Toast", Toast.LENGTH_LONG).show()
        }
    }
}