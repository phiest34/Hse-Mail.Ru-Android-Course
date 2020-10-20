package com.example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ui.CellsFragment.Companion.POSITION_KEY

class NumberFragment : Fragment() {
    private var bigNumber: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bigNumber = view.findViewById(R.id.big_number)

        val bundle: Bundle? = arguments
        val position: Int? = (bundle?.getInt(POSITION_KEY))?.plus(1)
        if (position != null) {
            bigNumber?.text = ((position).toString())
            bigNumber?.setTextColor(CellsFragment.getColor(position))
        }
    }
}
