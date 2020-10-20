package com.example.fitfitness.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitfitness.R
import com.example.fitfitness.data.Routine
import com.example.fitfitness.data.Workout
import kotlinx.android.synthetic.main.item_routine.view.*
import kotlinx.android.synthetic.main.item_workout.view.*

class RoutineAdapter(private var myRoutines: List<Routine>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RoutineAdapter.RoutineHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineAdapter.RoutineHolder {
        return RoutineHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_routine, parent, false), listener)
    }

    override fun getItemCount(): Int {
       return myRoutines.size
    }

    override fun onBindViewHolder(holder: RoutineAdapter.RoutineHolder, position: Int) {
        holder.routineName.text = myRoutines[position].routineName
    }

    fun setNewRoutines(routines: List<Routine>){
        this.myRoutines = routines;
        notifyDataSetChanged()
    }

    inner class RoutineHolder(view: View, listener: OnItemClickListener): BaseHolder(view, listener) {
        val routineName: TextView = view.routineName
    }
}