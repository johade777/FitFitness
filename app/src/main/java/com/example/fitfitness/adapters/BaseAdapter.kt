package com.example.fitfitness.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    var listItems: List<T>
//    var myHolder: RecyclerView.ViewHolder
//
//    constructor(listItems: List<T>, listener: OnItemClickListener, holder: RecyclerView.ViewHolder) {
//        this.listItems = listItems
//        myHolder = holder
//    }
//
//    fun setItems(listItems: List<T>) {
//        this.listItems = listItems
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return myHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false), viewType)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        onAdapterBindViewHolder(holder, position)
//    }
//    abstract  fun onAdapterBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
//
//    override fun getItemCount(): Int {
//        return listItems.size
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return getLayoutId(position, listItems[position])
//    }
//
//    protected abstract fun getLayoutId(position: Int, obj: T): Int
//
//    abstract fun getViewHolder(view: View, viewType: Int):RecyclerView.ViewHolder
//
//    interface OnItemClickListener {
//        fun onItemClick(position: Int)
//    }
//}

//abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    var listItems: List<T>
//
//    constructor(listItems: List<T>, listener: OnItemClickListener) {
//        this.listItems = listItems
//    }
//
//    constructor() {
//        listItems = emptyList()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return getViewHolder(LayoutInflater.from(parent.context)
//            .inflate(viewType, parent, false)
//            , viewType)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        (holder as Binder<T>).bind(listItems[position])
//    }
//
//    override fun getItemCount(): Int {
//        return listItems.size
//    }
//
//    abstract fun getViewHolder(view: View, viewType: Int):RecyclerView.ViewHolder
//
//    internal interface Binder<T> {
//        fun bind(data: T)
//    }
//
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttemptHolder {
////        return AttemptHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false))
////    }
////
////    override fun onBindViewHolder(holder: T, position: Int) {
////        holder.workoutDate.text = myAttempts[position].getDate()
////        holder.workoutWeight.text = myAttempts[position].weight.toString()
////        if(myAttempts[position].successful){
////            holder.workoutSuccess.text = "Pass"
////        }else{
////            holder.workoutSuccess.text = "Fail"
////        }
////    }
////
////    override fun getItemCount(): Int {
////        return myAttempts.size
////    }
//
////    inner class AttemptHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
////        val workoutDate = view.workoutDate
////        val workoutWeight = view.workoutWeight
////        val workoutSuccess = view.workoutSuccess
////
////        init {
////            view.setOnClickListener(this)
////        }
////
////        override fun onClick(v: View) {
////            if(adapterPosition != RecyclerView.NO_POSITION) {
////                listener.onItemClick(adapterPosition)
////            }
////        }
////    }
//
//    interface OnItemClickListener {
//        fun onItemClick(position: Int)
//    }
//}