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
import com.example.fitfitness.data.Attempt
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.viewmodel.activitymodels.ExerciseActivityViewModel
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_workout.*
import java.text.SimpleDateFormat
import java.time.temporal.ChronoField
import java.util.*
import kotlin.collections.ArrayList


class ExerciseActivity : AppCompatActivity(), OnItemClickListener{
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var exerciseViewModel: ExerciseActivityViewModel
    private lateinit var yAxis: YAxis
    private lateinit var xAxis: XAxis
    private var attemptAdapter = AttemptAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)
        exerciseViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            ExerciseActivityViewModel::class.java
        )

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
            setData(it)
            createXAxis(it)
        })
        chart.setNoDataText("No Progress Yet!")
        chart.setTouchEnabled(false)
        chart.isDragEnabled = false
        chart.isScaleXEnabled = false
        chart.description.isEnabled = false
        chart.legend.isEnabled = false

        xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.axisLineWidth = 3f
        xAxis.textSize = 12f
        xAxis.textColor = Color.BLACK
        xAxis.valueFormatter = DateAxisValueFormatter()
        xAxis.setDrawLimitLinesBehindData(true)
        xAxis.setDrawGridLines(false)

        yAxis = chart.axisLeft
        yAxis.removeAllLimitLines()
        yAxis.axisLineWidth = 3f
        yAxis.setDrawAxisLine(false)
        yAxis.setDrawGridLines(false)
        yAxis.textSize = 12f
        yAxis.granularity = 1f
        yAxis.textColor = Color.BLACK
        yAxis.setDrawZeroLine(false)
        yAxis.setDrawLimitLinesBehindData(false)
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 10f

        chart.axisRight.isEnabled = false
//        progressChart.animateX(2500, Easing.EasingOption.EaseInOutQuart)

//        setData()
        chart.invalidate()
    }

    private fun setData(attempts: List<Attempt>){
        var values: ArrayList<Entry>  = ArrayList()
        var yMin: Float? = null
        var yMax: Float? = null

        for((index, value) in attempts.reversed().withIndex()){
            var yPoint: Float = value.weight
            var xPointDate: Float = value.date!!.getLong(ChronoField.EPOCH_DAY).toFloat()
            values.add(Entry(xPointDate, value.weight))

            if(yMin == null && yMax == null){
                yMin = value.weight;
                yMax = value.weight
                continue
            }

            if(value.weight < yMin!!){
                yMin == value.weight
                continue
            }

            if(value.weight > yMax!!){
                yMax = value.weight
                continue
            }
        }

        if(yMax != null && yMin != null){
            var range = yMax - yMin
            var delta = range * .2f
            yMax += delta
            yMin -= delta

            if(yMin < 0){
                yMin = 0f
            }

            yAxis.axisMaximum = yMax
            yAxis.axisMinimum = yMin
        }

        var set1 = LineDataSet(values, "")
        set1.lineWidth = 3f
        set1.setDrawFilled(true)
        set1.setDrawCircles(false)
        set1.circleRadius = 4f

        set1.color = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        set1.setCircleColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        var dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1)

        var data = LineData(dataSets)
        data.setValueTextSize(9f);
        data.setDrawValues(false);

        chart.data = data
    }

    private fun createXAxis(attempts: List<Attempt>) : MutableCollection<String>{
        var values: MutableCollection<String>  = ArrayList()

        for((index, value) in attempts.reversed().withIndex()){
            values.add(value.getDateString())
        }

        return values
    }

    internal class DateAxisValueFormatter() : IndexAxisValueFormatter() {
        var sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)

        override fun getFormattedValue(value: Float, axis: AxisBase): String {
            return sdf.format(Date(value.toLong()))
        }
    }

    override fun onItemClick(position: Int) {
//        Toast.makeText(this, attemptList[position].workoutDate, Toast.LENGTH_LONG).show()
    }
}