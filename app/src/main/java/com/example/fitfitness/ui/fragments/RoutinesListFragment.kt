package com.example.fitfitness.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitfitness.R
import com.example.fitfitness.adapters.RoutineAdapter
import com.example.fitfitness.data.Routine
import com.example.fitfitness.ui.AddRoutineActivity
import com.example.fitfitness.ui.AddWorkoutActivity
import com.example.fitfitness.viewmodel.fragmentmodels.RoutinesListFragmentViewModel

class RoutinesListFragment : BaseListFragment() {
    private lateinit var routinesViewModel: RoutinesListFragmentViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var routines: List<Routine> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        routinesViewModel = ViewModelProviders.of(this).get(RoutinesListFragmentViewModel::class.java)
        setHasOptionsMenu(true)

        val root = inflater.inflate(R.layout.fragment_routines, container, false)
        val textView: TextView = root.findViewById(R.id.text_routine)
        val routineList: RecyclerView = root.findViewById(R.id.routine_list_frag)
        val adapter = RoutineAdapter(routines, this)

        routinesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        routinesViewModel.mRoutineLiveData.observe(viewLifecycleOwner, Observer {
            routines = it
            adapter.setNewRoutines(it)
        })

        linearLayoutManager = LinearLayoutManager(root.context)
        routineList.layoutManager = linearLayoutManager
        routineList.adapter = adapter

        return root
    }

    override fun onItemClick(position: Int) {
//        val intent = Intent(context, WorkoutActivity::class.java)
//        intent.putExtra("workout", work[position])
//        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add -> {
                val intent = Intent(context, AddRoutineActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }
}