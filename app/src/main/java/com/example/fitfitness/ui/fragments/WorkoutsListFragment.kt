package com.example.fitfitness.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitfitness.R
import com.example.fitfitness.adapters.WorkoutAdapter
import com.example.fitfitness.data.Workout
import com.example.fitfitness.viewmodel.fragmentmodels.WorkoutsListFragmentViewModel

class WorkoutsListFragment : BaseListFragment() {

    private lateinit var workoutsViewModel: WorkoutsListFragmentViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var fav: MenuItem
    private var workouts: List<Workout> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        workoutsViewModel = ViewModelProviders.of(this).get(WorkoutsViewModel::class.java)
        workoutsViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            WorkoutsListFragmentViewModel::class.java)
        setHasOptionsMenu(true)

        val root = inflater.inflate(R.layout.fragment_workouts, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        val workoutList: RecyclerView = root.findViewById(R.id.workout_frag_list)
        val adapter = WorkoutAdapter(workouts, this)

        workoutsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        workoutsViewModel.mWorkoutLiveData.observe(viewLifecycleOwner, Observer {
            workouts = it
            adapter.setNewWorkouts(it)
        })

        linearLayoutManager = LinearLayoutManager(root.context)
        workoutList.layoutManager = linearLayoutManager
        workoutList.adapter = adapter

        return root
    }

    override fun onItemClick(position: Int) {
//        TODO("Not yet implemented")
        Toast.makeText(context, "Temp", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }
}