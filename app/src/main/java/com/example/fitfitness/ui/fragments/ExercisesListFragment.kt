package com.example.fitfitness.ui.fragments

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitfitness.R
import com.example.fitfitness.adapters.ExerciseAdapter
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.ui.AddExerciseActivity
import com.example.fitfitness.ui.ExerciseActivity
import com.example.fitfitness.ui.components.SwipeController
import com.example.fitfitness.ui.components.SwipeControllerActions
import com.example.fitfitness.viewmodel.fragmentmodels.ExerciseListFragmentViewModel


class ExercisesListFragment : BaseListFragment() {
    private lateinit var exercisesViewModel: ExerciseListFragmentViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var exercises: ArrayList<Exercise> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        exercisesViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(ExerciseListFragmentViewModel::class.java)
        setHasOptionsMenu(true)

        val root = inflater.inflate(R.layout.fragment_exercise, container, false)
        val textView: TextView = root.findViewById(R.id.text_exercise)
        val exerciseList: RecyclerView = root.findViewById(R.id.exercise_list_frag)
        val adapter = ExerciseAdapter(exercises, this, container!!.context)

        val swipeController = SwipeController(object : SwipeControllerActions() {
            override fun onRightClicked(position: Int) {
                adapter.removeExercise(position)
            }

            override fun onLeftClicked(position: Int) {
            }
        }, adapter, container!!.context)

        var itemTouchHelper = ItemTouchHelper(swipeController)
        itemTouchHelper.attachToRecyclerView(exerciseList)
        exerciseList.addItemDecoration(object: RecyclerView.ItemDecoration(){
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                swipeController.onDraw(c)
            }
        })

        exercisesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        exercisesViewModel.mExerciseLiveData.observe(viewLifecycleOwner, Observer {
            exercises = ArrayList(it)
            adapter.setNewExercises(exercises)
        })

        linearLayoutManager = LinearLayoutManager(root.context)
        exerciseList.layoutManager = linearLayoutManager
        exerciseList.adapter = adapter

        return root
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(context, ExerciseActivity::class.java)
        intent.putExtra("exercise", exercises[position])
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add -> {
                val intent = Intent(context, AddExerciseActivity::class.java)
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