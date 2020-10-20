package com.example.fitfitness.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_progress.view.*

abstract class BaseHolder(view: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(adapterPosition != RecyclerView.NO_POSITION) {
            listener.onItemClick(adapterPosition)
        }
    }
}