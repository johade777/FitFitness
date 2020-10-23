package com.example.fitfitness.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.fitfitness.R
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.viewmodel.activitymodels.AddExerciseViewModel
import com.example.fitfitness.viewmodel.activitymodels.ExerciseActivityViewModel
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_add_exercise.*

class AddExerciseActivity : AppCompatActivity() {
    private lateinit var viewModel: AddExerciseViewModel
    private lateinit var loadingHud: KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)

        loadingHud = KProgressHUD(this)
        loadingHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)

        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            AddExerciseViewModel::class.java
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

        submit_button.setOnClickListener {
            val exerciseName = exercise_Name_Input.text.toString()
            val primaryMuscle = primary_Muscle_Input.text.toString()
            val exerciseWeight = weight_Input.text.toString().toFloat()
            val exerciseRepetitions = repetitions_Input.text.toString().toInt()
            val exerciseSets = set_Count_Input.text.toString().toInt()

            val exercise = Exercise(exerciseName, primaryMuscle, exerciseWeight, exerciseRepetitions, exerciseSets)
            viewModel.saveExercise(exercise)
//            Toast.makeText(this, "Submit Toast", Toast.LENGTH_LONG).show()
        }

        cancel_button.setOnClickListener {
            Toast.makeText(this, "Cancel Toast", Toast.LENGTH_LONG).show()
        }
    }
}