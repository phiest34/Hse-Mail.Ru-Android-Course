package com.example.ui

import adapter.CellAdapter
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import objects.Cell

class cellFragment : Fragment() {
    companion object {
        fun newInstance() = cellFragment()
    }

    private lateinit var viewModel: CellViewModel

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
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        data = initializeData()
        recyclerView = view?.findViewById(R.id.recycler_view_item)
        gridLayoutManager =
            GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        cellAdapter = CellAdapter(data!!)
        recyclerView?.adapter = cellAdapter
    }

    private fun initializeData(): ArrayList<Cell> {
        val data = ArrayList<Cell>()
        for (position in 1..100) {
            val color: Int = if (position % 2 == 0) {
                Color.RED
            } else {
                Color.BLUE
            }
            val cell = Cell(position, color)
            data.add(cell)
        }
        return data
    }

}