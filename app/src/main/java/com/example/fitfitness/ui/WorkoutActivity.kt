package com.example.fitfitness.ui

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fitfitness.R
import com.example.fitfitness.adapters.ExerciseAdapter
import com.example.fitfitness.adapters.OnItemClickListener
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Workout
import com.example.fitfitness.viewmodel.activitymodels.WorkoutActivityViewModel
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_workout_list.*


class WorkoutActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var workoutViewModel: WorkoutActivityViewModel
    private lateinit var loadingHud: KProgressHUD
    private var exercises: List<Exercise> = ArrayList()
    private val adapter = ExerciseAdapter(exercises, this)
    val paint: Paint = Paint()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_list)

        val mIth = ItemTouchHelper(object : SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
                return true // true if moved, false otherwise
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                // remove from adapter
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                try {
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                        val itemView = viewHolder.itemView
                        val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                        val width = height / 5
                        viewHolder.itemView.translationX = dX / 5
//                            paint.color = Color.parseColor("#D32F2F")
//                            val background = RectF(itemView.right.toFloat() + dX / 5, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
//                            c.drawRect(background, paint)
//                            icon = BitmapFactory.decodeResource(resources, R.drawable.ic_home_black_24dp)
//                            val icon_dest = RectF((itemView.right + dX / 7), itemView.top.toFloat() + width, itemView.right.toFloat() + dX / 20, itemView.bottom.toFloat() - width)
//                            c.drawBitmap(icon, null, icon_dest, paint)
                    } else {
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun getSwipeThreshold(viewHolder: ViewHolder): Float {
                return .5f
            }
        })

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

        workoutViewModel.getWorkoutVolumeLiveData().observe(this, {
            workoutVolume.text = "$it lb"
        })

        linearLayoutManager = LinearLayoutManager(this)
        exercise_list.layoutManager = linearLayoutManager
        exercise_list.adapter = adapter
        mIth.attachToRecyclerView(exercise_list)

        workoutName.text = workout?.name
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, ExerciseActivity::class.java)
        intent.putExtra("exercise", exercises[position])
        startActivity(intent)
    }
}
