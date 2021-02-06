package com.example.fitfitness.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fitfitness.R
import com.example.fitfitness.data.Routine
import com.example.fitfitness.data.Workout
import com.example.fitfitness.viewmodel.activitymodels.AddRoutineViewModel
import com.example.fitfitness.viewmodel.activitymodels.AddWorkoutViewModel
import com.google.android.material.button.MaterialButton
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_add_exercise.*
import kotlinx.android.synthetic.main.activity_add_exercise.cancel_button
import kotlinx.android.synthetic.main.activity_add_exercise.submit_button
import kotlinx.android.synthetic.main.activity_add_routine.*
import kotlinx.android.synthetic.main.activity_add_workout.*
import kotlinx.android.synthetic.main.view_weekday_picker.*

class AddRoutineActivity : AppCompatActivity() {
    private lateinit var viewModel: AddRoutineViewModel
    private lateinit var loadingHud: KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_routine)

        loadingHud = KProgressHUD(this)
        loadingHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)

        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            AddRoutineViewModel::class.java
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
            val routineName = routine_Name_Input.text.toString()
            if(routineName.isNotEmpty()){
                val routine = Routine(routineName)
                viewModel.saveRoutine(routine)
            }
        }

        cancel_button.setOnClickListener {
            Toast.makeText(this, "Cancel Toast", Toast.LENGTH_LONG).show()
        }
    }
}