package com.example.fitfitness.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.example.fitfitness.R
import com.example.fitfitness.viewmodel.activitymodels.AddWorkoutViewModel
import kotlinx.android.synthetic.main.fragment_workout_name.view.*
/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutNameFragment : Fragment() {
    private lateinit var workoutNameInput: EditText
    private val viewModel: AddWorkoutViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_workout_name, container, false)
        workoutNameInput = rootView.workout_Name_Input
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment WorkoutName.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = WorkoutNameFragment().apply {}
    }

    fun setWorkoutName(){
        viewModel.workoutName = workoutNameInput.text.toString()
    }
}