package com.example.fitfitness.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fitfitness.R
import com.example.fitfitness.viewmodel.fragmentmodels.TodayFragmentViewModel

class TodayFragment : Fragment() {
    private lateinit var todayViewModel: TodayFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        todayViewModel = ViewModelProviders.of(this).get(TodayFragmentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_today, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)

        todayViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}