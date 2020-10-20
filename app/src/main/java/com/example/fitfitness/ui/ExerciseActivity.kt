package com.example.fitfitness.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitfitness.R
import com.example.fitfitness.adapters.AttemptAdapter
import com.example.fitfitness.adapters.OnItemClickListener
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.data.Attempt
import com.example.fitfitness.viewmodel.activitymodels.ExerciseActivityViewModel
import com.example.fitfitness.viewmodel.fragmentmodels.ExerciseListFragmentViewModel
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_workout.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ExerciseActivity : AppCompatActivity(), OnItemClickListener{
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var exerciseViewModel: ExerciseActivityViewModel
    private var attemptAdapter = AttemptAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)
        exerciseViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(ExerciseActivityViewModel::class.java)

        val selectedExercise = intent.getSerializableExtra("exercise") as? Exercise

        var exerciseNameText = selectedExercise?.name
        var exercisePrimary = selectedExercise?.primaryMuscle;

        exerciseName.text = exerciseNameText
        exerciseTypes.text = exercisePrimary

        linearLayoutManager = LinearLayoutManager(this)
        attempt_list.layoutManager = linearLayoutManager
        attempt_list.adapter = attemptAdapter

        exerciseViewModel.getExerciseWithAttempts(selectedExercise!!.exerciseId).observe(this, {
            attemptAdapter.setAttempts(it)
        })
        progressChart.setNoDataText("No Progress Yet!")
        progressChart.setTouchEnabled(false)
        progressChart.isDragEnabled = false
        progressChart.isScaleXEnabled = false

        val xAxis: XAxis = progressChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.axisLineWidth = 3f
        xAxis.textSize = 20f
        xAxis.textColor = Color.WHITE
//        xAxis.valueFormatter = DateAxisValueFormatter(createXAxis())
        xAxis.setDrawLimitLinesBehindData(true)
        xAxis.setDrawGridLines(false)

        val yAxis: YAxis = progressChart.axisLeft
        yAxis.removeAllLimitLines()
        yAxis.axisLineWidth = 3f
        yAxis.setDrawAxisLine(false)
        yAxis.setDrawGridLines(false)
        yAxis.textSize = 20f
        yAxis.granularity = 1f
        yAxis.textColor = Color.WHITE
        yAxis.setDrawZeroLine(false)
        yAxis.setDrawLimitLinesBehindData(false)

        progressChart.axisRight.isEnabled = false
//        progressChart.animateX(2500, Easing.EasingOption.EaseInOutQuart)

//        setData()
        progressChart.invalidate()
    }

    private fun setData(attempts: List<Attempt>){
        var values: ArrayList<Entry>  = ArrayList()

        for((index, value) in attempts.reversed().withIndex()){
            var yPoint: Float = value.weight
//            var xPointDateString: String = value.getDate()
            values.add(Entry(index.toFloat(), value.weight))
        }

        var d1 = LineDataSet(values, "Set 1")
        d1.lineWidth = 3f;
        d1.circleRadius = 2f;
        d1.color = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        d1.setCircleColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        var dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(d1)

        var data = LineData(dataSets)
        progressChart.data = data
    }

    private fun createXAxis(attempts: List<Attempt>) : MutableCollection<String>{
        var values: MutableCollection<String>  = ArrayList()

        for((index, value) in attempts.reversed().withIndex()){
            values.add(value.getDateString())
        }

        return values
    }

//    class MyValueFormatter: IValueFormatter {
//        override fun getFormattedValue(value: Float, entry: Entry?, dataSetIndex: Int, viewPortHandler: ViewPortHandler?): String {
//            TODO("Not yet implemented")
//        }
//    }
//
//    class DateAxisValueFormatter(values: MutableCollection<String>?) : IndexAxisValueFormatter(values) {
//        var sdf: SimpleDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
//
//        override fun getFormattedValue(value: Float, axis: AxisBase): String {
//            // "value" represents the position of the label on the axis (x or y)
//            var temp = value.toInt()
//            return values[value.toInt()]
//        }
//
//    }

    override fun onItemClick(position: Int) {
//        Toast.makeText(this, attemptList[position].workoutDate, Toast.LENGTH_LONG).show()
    }
}