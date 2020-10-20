package com.example.fitfitness.room.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.fitfitness.data.Routine
import com.example.fitfitness.room.FitDatabase
import com.example.fitfitness.room.dao.RoutineDao

open class RoutineRepository(context: Context) {

    private lateinit var routineDao: RoutineDao
    private lateinit var allRoutines: LiveData<List<Routine>>

    init {
        FitDatabase.getInstance(context)?.let {
            routineDao = it.routineDao()
            allRoutines = routineDao.getAllRoutines()
        }
    }

    fun insert(routine: Routine) {
        DoInBackgroundAsync<Routine> {
            routineDao.insert(routine)
        }.execute()
    }

    fun getAllRoutines(): LiveData<List<Routine>> {
        return allRoutines
    }

    fun updateRoutines(routine: Routine) {
        DoInBackgroundAsync<Routine> {
            routineDao.update(routine)
        }.execute()
    }

    fun removeRoutine(routine: Routine) {
        DoInBackgroundAsync<Routine> {
            routineDao.remove(routine)
        }.execute()
    }

    private class DoInBackgroundAsync<T : Any>(private val backgroundTask: () -> Unit) : AsyncTask<T, Unit, Unit>() {
        override fun doInBackground(vararg params: T) {
            backgroundTask.invoke()
        }
    }
}