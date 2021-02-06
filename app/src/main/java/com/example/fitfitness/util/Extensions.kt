package com.example.fitfitness.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun Date.toSimpleString() : String {
    val format = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
    return format.format(this)
}

fun getDateInMilliSeconds(givenDateString: String?, format: String): Long {
    val sdf = SimpleDateFormat(format, Locale.US)
    var timeInMilliseconds: Long = 1
    try {
        val mDate = sdf.parse(givenDateString)
        timeInMilliseconds = mDate.time
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return timeInMilliseconds
}

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}