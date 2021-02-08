package com.example.fitfitness.ui.fragments

import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitfitness.R
import com.example.fitfitness.adapters.ExerciseAdapter
import com.example.fitfitness.adapters.OnItemClickListener
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.viewmodel.activitymodels.AddWorkoutViewModel
import kotlinx.android.synthetic.main.fragment_workout_add_exercises.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutAddExercisesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutAddExercisesFragment : Fragment(), OnItemClickListener {
    private lateinit var exerciseSearch: SearchView
    private val viewModel: AddWorkoutViewModel by activityViewModels()
    private var exerciseList: List<String> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var exercises: ArrayList<Exercise> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_workout_add_exercises, container, false)

        exerciseSearch = rootView.exercise_Search

        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.item_label)
        val cursorAdapter = SimpleCursorAdapter(activity, R.layout.search_item, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)
        exerciseSearch.suggestionsAdapter = cursorAdapter

        exerciseSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                hideKeyboard()
                viewModel.addExercise(query!!)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val cursor = MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                query?.let {
                    exerciseList.forEachIndexed { index, suggestion ->
                        if (suggestion.contains(query, true))
                            cursor.addRow(arrayOf(index, suggestion))
                    }
                }

                cursorAdapter.changeCursor(cursor)
                return true
            }

        })

        exerciseSearch.setOnSuggestionListener(object: SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val cursor = exerciseSearch.suggestionsAdapter.getItem(position) as Cursor
                val selection = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                exerciseSearch.setQuery(selection, false)
                return true
            }
        })

        val exerciseList: RecyclerView = rootView.findViewById(R.id.exercise_list_frag)
        val adapter = ExerciseAdapter(exercises, this)

        linearLayoutManager = LinearLayoutManager(rootView.context)
        exerciseList.layoutManager = linearLayoutManager
        exerciseList.adapter = adapter

        viewModel.getSelectedExercisesLiveData().observe(viewLifecycleOwner, {
            adapter.setNewExercises(ArrayList(it))
        })

        return rootView
    }

    fun setExerciseList(it: List<String>) {
        exerciseList = it
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment WorkoutAddExercisesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = WorkoutAddExercisesFragment().apply {}
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }
}