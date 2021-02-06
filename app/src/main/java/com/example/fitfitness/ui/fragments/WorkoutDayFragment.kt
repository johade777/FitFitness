package com.example.fitfitness.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.fitfitness.R
import com.example.fitfitness.viewmodel.activitymodels.AddWorkoutViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.view_weekday_picker.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutDayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutDayFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
    private val viewModel: AddWorkoutViewModel by activityViewModels()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_workout_day, container, false)

        rootView.toggle_button_group.addOnButtonCheckedListener {group, checkedId, isChecked ->
            if(group.checkedButtonId == -1) {
                group.check(checkedId)
            }

            for(buttonIndex in 0 until rootView.toggle_button_group.childCount){
                val child = rootView.toggle_button_group.getChildAt(buttonIndex) as MaterialButton
                if(child.isChecked){
                    child.setBackgroundColor(Color.GREEN)
                    viewModel.selectedDay = buttonIndex + 1
                }else{
                    child.setBackgroundColor(Color.WHITE)
                }
            }
        }

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment WorkoutDayFragment.
         */
        @JvmStatic
        fun newInstance() = WorkoutDayFragment().apply {}

//        fun newInstance(param1: String, param2: String) =
//            WorkoutDayFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}