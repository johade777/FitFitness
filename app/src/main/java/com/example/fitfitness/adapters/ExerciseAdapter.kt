package com.example.fitfitness.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitfitness.R
import com.example.fitfitness.adapters.ExerciseAdapter.*
import com.example.fitfitness.data.Exercise
import kotlinx.android.synthetic.main.item_exercise.view.*

class ExerciseAdapter(private var myExercises: List<Exercise>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ExerciseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        return ExerciseHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false), listener)
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        holder.exerciseName.text = myExercises[position].name
        holder.exerciseWeight.text = "Weight: " + myExercises[position].currentWeight.toString();
        holder.exerciseReps.text = "Repetitions: " + myExercises[position].reps.toString();
        holder.exerciseSets.text = "Sets: " + myExercises[position].sets.toString();
        holder.exercisePrimary.text = "Muscle: " + myExercises[position].primaryMuscle
    }

    override fun getItemCount(): Int {
        return myExercises.size
    }

    fun setNewExercises(exercises: List<Exercise>){
        this.myExercises = exercises;
        notifyDataSetChanged()
    }

    inner class ExerciseHolder(view: View, listener: OnItemClickListener): BaseHolder(view, listener) {
        val exerciseName: TextView = view.name;
        val exerciseWeight: TextView = view.weight;
        val exerciseReps: TextView = view.repetitions;
        val exerciseSets: TextView = view.sets;
        val exercisePrimary: TextView = view.primary_muscle
    }
}
