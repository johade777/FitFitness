package com.example.fitfitness.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.fitfitness.R
import com.example.fitfitness.adapters.ExerciseAdapter.*
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.room.FitDatabase
import kotlinx.android.synthetic.main.item_exercise.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExerciseAdapter(private var myExercises: MutableList<Exercise>, private val listener: OnItemClickListener, private val context: Context) : BaseAdapter<ExerciseHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        return ExerciseHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false), listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ExerciseHolder
        holder.exerciseName.text = myExercises[position].name
        holder.exerciseWeight.text = "Weight: " + myExercises[position].currentWeight.toString();
        holder.exerciseReps.text = "Repetitions: " + myExercises[position].reps.toString();
        holder.exerciseSets.text = "Sets: " + myExercises[position].sets.toString();
        holder.exercisePrimary.text = "Muscle: " + myExercises[position].primaryMuscle
    }

    override fun getItemCount(): Int {
        return myExercises.size
    }

    fun setNewExercises(exercises: ArrayList<Exercise>){
        this.myExercises = exercises.toMutableList();
        notifyDataSetChanged()
    }

    fun removeExercise(position: Int){
        myExercises.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ExerciseHolder(view: View, listener: OnItemClickListener): BaseHolder(view, listener) {
        val exerciseName: TextView = view.name;
        val exerciseWeight: TextView = view.weight;
        val exerciseReps: TextView = view.repetitions;
        val exerciseSets: TextView = view.sets;
        val exercisePrimary: TextView = view.primary_muscle
    }
}
