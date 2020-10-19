package com.example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import timber.log.Timber


class NumberFragment() : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_number, container, false)
    }

    private var bigNumber: TextView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bigNumber = view.findViewById(R.id.big_number)
        var bundle: Bundle? = null

        bundle = arguments
        val position: Int? = bundle?.getInt("position")
        Timber.i("CEll $position CLICKED")
        if (position != null) {
            Timber.i("${bigNumber?.text}")
            bigNumber?.text = ((position + 1).toString())
            bigNumber?.setTextColor(CellsFragment.getColor(position + 1))
        }

    }
}

