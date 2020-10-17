package com.example.ui

import adapter.CellAdapter
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import objects.Cell

class CellFragment : Fragment() {
    companion object {
        fun newInstance() = CellFragment()
        fun getColor(number: Int): Int {
            return if (number % 2 == 0) {
                Color.RED
            } else {
                Color.BLUE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cell_fragment, container, false)
    }

    private var recyclerView: RecyclerView? = null

    private var gridLayoutManager: GridLayoutManager? = null

    private var data: ArrayList<Cell>? = null

    private var cellAdapter: CellAdapter? = null

    private var count: Int = 100

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) {
            data = initializeData(count)
        } else {
            count = savedInstanceState.getInt("count")
            data = initializeData(count)
        }
        recyclerView = view?.findViewById(R.id.recycler_view_item)
        gridLayoutManager =
            GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        cellAdapter = CellAdapter(data!!)
        recyclerView?.adapter = cellAdapter
        val button: Button = view?.findViewById(R.id.containedButton) as Button
        button.setOnClickListener {
            Toast.makeText(context, "Button clicked", Toast.LENGTH_LONG).show()
            cellAdapter?.addCell()
            count++
            cellAdapter?.notifyDataSetChanged()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
    }

    private fun initializeData(count: Int): ArrayList<Cell> {
        val data = ArrayList<Cell>()
        for (position in 1..count) {
            val cell = Cell(position, getColor(position))
            data.add(cell)
        }
        return data
    }


}
