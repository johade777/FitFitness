package com.example.fitfitness.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitfitness.R
import com.example.fitfitness.data.Attempt
import kotlinx.android.synthetic.main.item_progress.view.*

class AttemptAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<AttemptAdapter.AttemptHolder>() {
    private var myAttempts: List<Attempt> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttemptHolder {
        return AttemptHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false), listener)
    }

    override fun onBindViewHolder(holder: AttemptHolder, position: Int) {
        holder.workoutDate.text = myAttempts[position].getDateString()
        holder.workoutWeight.text = myAttempts[position].weight.toString()
        if(myAttempts[position].successful){
            holder.workoutSuccess.text = "Pass"
        }else{
            holder.workoutSuccess.text = "Fail"
        }
    }

    override fun getItemCount(): Int {
        return myAttempts.size
    }

    fun setAttempts(attempts: List<Attempt>){
        myAttempts = attempts;
        notifyDataSetChanged()
    }

    inner class AttemptHolder(view: View, listener: OnItemClickListener): BaseHolder(view, listener){
        val workoutDate: TextView = view.workoutDate
        val workoutWeight: TextView = view.workoutWeight
        val workoutSuccess: TextView = view.workoutSuccess
    }
}