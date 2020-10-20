package com.example.fitfitness.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fitfitness.R
import com.example.fitfitness.data.Exercise
import com.example.fitfitness.room.FitDatabase

class MainActivityBottomNav : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bottom_nav)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_today, R.id.navigation_exercises, R.id.navigation_workouts, R.id.navigation_routines
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        val db: FitDatabase = FitDatabase.getInstance(this)!!
//
//        db.exerciseDao().insert(Exercise(1,"Curl", "Bicep", 25.0f, 10, 3))
//        db.exerciseDao().insert(Exercise(2,"Bench Press", "Pectoral", 140.0f, 5, 4))
//        db.exerciseDao().insert(Exercise(3,"Dead Lift", "Hamstring", 280.0f, 5, 5));
//
//        var text = "wait";
    }


}