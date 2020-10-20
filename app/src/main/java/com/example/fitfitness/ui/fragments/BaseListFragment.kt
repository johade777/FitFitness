package com.example.fitfitness.ui.fragments

import androidx.fragment.app.Fragment
import com.example.fitfitness.adapters.OnItemClickListener

abstract class BaseListFragment : Fragment(), OnItemClickListener {
    abstract override fun onItemClick(position: Int)
}