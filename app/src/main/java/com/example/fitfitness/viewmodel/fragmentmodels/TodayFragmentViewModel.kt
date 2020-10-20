package com.example.fitfitness.viewmodel.fragmentmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodayFragmentViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Today Fragment"
    }
    val text: LiveData<String> = _text
}