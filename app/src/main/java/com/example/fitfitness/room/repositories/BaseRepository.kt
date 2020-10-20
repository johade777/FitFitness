package com.example.fitfitness.room.repositories

import android.os.AsyncTask

open class BaseRepository() {
    protected class DoInBackgroundAsync<T : Any>(private val backgroundTask: () -> Unit) :
        AsyncTask<T, Unit, Unit>() {
        override fun doInBackground(vararg params: T) {
            backgroundTask.invoke()
        }
    }
}