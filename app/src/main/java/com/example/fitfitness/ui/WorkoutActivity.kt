package com.example.fitfitness.ui

import android.content.Intent
import android.graphics.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitfitness.R
import com.example.fitfitness.adapters.ExerciseAdapter
import com.example.fitfitness.adapters.OnItemClickListener
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Workout
import com.example.fitfitness.ui.components.SwipeController
import com.example.fitfitness.ui.components.SwipeControllerActions
import com.example.fitfitness.viewmodel.activitymodels.WorkoutActivityViewModel
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_workout_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class WorkoutActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var workoutViewModel: WorkoutActivityViewModel
    private lateinit var loadingHud: KProgressHUD
    private val CREATE_EXERCISE_RESULT = 1;
    private var exercises: ArrayList<Exercise> = ArrayList()
    private val adapter = ExerciseAdapter(exercises, this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_list)

        loadingHud = KProgressHUD(this)
        loadingHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)

        val workout = intent.getSerializableExtra("workout") as? Workout
        workoutViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            WorkoutActivityViewModel::class.java
        )

        add_exercise_button.setOnClickListener {
            val intent = Intent(this, AddExerciseActivity::class.java)
            startActivityForResult(intent, CREATE_EXERCISE_RESULT)
        }

        workoutViewModel.getWorkoutsWithExercises(workout!!.workoutId).observe(this, {
            exercises = ArrayList(it.exercises)
            adapter.setNewExercises(exercises)
        })

        workoutViewModel.getWorkoutVolumeLiveData().observe(this, {
            workoutVolume.text = "$it lb"
        })

        workoutViewModel.isLoading.observe(this, {
            if(it){
                loadingHud.show()
            }else{
                loadingHud.dismiss()
            }
        })

        workoutViewModel.loadingString.observe(this, {
            loadingHud.setLabel(it)
        })

        linearLayoutManager = LinearLayoutManager(this)
        exercise_list.layoutManager = linearLayoutManager
        exercise_list.adapter = adapter

        val swipeController = SwipeController(object : SwipeControllerActions() {
            override fun onRightClicked(position: Int) {
                runBlocking {
                    withContext(Dispatchers.IO){
                        workoutViewModel.deleteExercise(exercises[position].exerciseId, workout.workoutId)
                    }
                }
                adapter.removeExercise(position)
            }

            override fun onLeftClicked(position: Int) {
            }
        }, this)

        var itemTouchHelper = ItemTouchHelper(swipeController)
        itemTouchHelper.attachToRecyclerView(exercise_list)
        exercise_list.addItemDecoration(object: RecyclerView.ItemDecoration(){
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                swipeController.onDraw(c)
            }
        })

        workoutName.text = workout?.name
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == CREATE_EXERCISE_RESULT){
            var text = "wait";
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, ExerciseActivity::class.java)
        intent.putExtra("exercise", exercises[position])
        startActivity(intent)
    }
}
