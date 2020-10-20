package com.example.fitfitness.viewmodel.fragmentmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitfitness.data.Routine
import com.example.fitfitness.room.repositories.RoutineRepository

class RoutinesListFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private var routineRepository = RoutineRepository(application.applicationContext)
    var mRoutineLiveData: LiveData<List<Routine>>

    private var _text = MutableLiveData<String>().apply {
        value = "This is Routines Fragment"
    }
//    val mRoutineLiveData = MutableLiveData<MutableList<Routine>>()
    val text: LiveData<String> = _text

    init {
        mRoutineLiveData = routineRepository.getAllRoutines()
    }

//    private fun addRoutine(routine: Routine) {
//        mRoutineLiveData.value?.add(routine)
//        mRoutineLiveData.notifyObserver()
//    }
}