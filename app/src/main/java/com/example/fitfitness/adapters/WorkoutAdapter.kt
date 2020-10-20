package com.example.fitfitness.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitfitness.R
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Workout
import kotlinx.android.synthetic.main.item_workout.view.*
import java.util.*
import kotlin.collections.ArrayList

class WorkoutAdapter(private var myWorkouts: List<Workout>, private val listener: OnItemClickListener) : RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutHolder {
        return WorkoutHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false), listener)
    }

    override fun onBindViewHolder(holder: WorkoutHolder, position: Int) {
        holder.workoutName.text = myWorkouts[position].name
        holder.workoutDay.text = getWorkoutDay(myWorkouts[position].day)
    }

    override fun getItemCount(): Int {
        return myWorkouts.size
    }

    fun setNewWorkouts(workouts: List<Workout>){
        this.myWorkouts = workouts;
        notifyDataSetChanged()
    }

    private fun getWorkoutDay(dayOf: Int): String {
        return when(dayOf){
            1 -> "Sunday"
            2 -> "Monday"
            3 -> "Tuesday"
            4 -> "Wednesday"
            5 -> "Thursday"
            6 -> "Friday"
            7 -> "Saturday"
            else -> "Unknown"
        }
    }

    inner class WorkoutHolder(view: View, listener: OnItemClickListener): BaseHolder(view, listener) {
        val workoutName: TextView = view.workoutName
        val workoutDay: TextView = view.workoutDay
    }
}