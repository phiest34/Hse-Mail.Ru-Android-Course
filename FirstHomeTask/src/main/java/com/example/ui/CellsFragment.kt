package com.example.ui

import adapter.CellAdapter
import adapter.CellViewHolder
import android.content.Context
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import objects.Cell
import objects.Cell.Companion.DATA_SIZE_START_VALUE

class CellsFragment : Fragment() {
    companion object {
        const val COUNT_KEY = "count"

        const val POSITION_KEY = "position"

        const val PORTRAIT_ORIENTATION_CELLS = 3

        const val LANDSCAPE_ORIENTATION_CELLS = 4

        fun getColor(number: Int): Int {
            return if (number % 2 == 0) {
                Color.RED
            } else {
                Color.BLUE
            }
        }
    }


    interface IListener {
        fun onCellClicked(cell: Cell)
    }


    private var listener: IListener? = null

    private var recyclerView: RecyclerView? = null

    private var gridLayoutManager: GridLayoutManager? = null

    private var data: ArrayList<Cell>? = null

    private var cellAdapter: CellAdapter? = null

    private var count: Int = DATA_SIZE_START_VALUE



    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = requireActivity() as? IListener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cell_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            data = initializeData(count)
        } else {
            count = savedInstanceState.getInt(COUNT_KEY)
            data = initializeData(count)
        }

        recyclerView = view.findViewById(R.id.recycler_view_item)
        gridLayoutManager =
            if (resources.configuration.orientation == SCREEN_ORIENTATION_PORTRAIT) {
                GridLayoutManager(activity, PORTRAIT_ORIENTATION_CELLS, LinearLayoutManager.VERTICAL, false)
            } else {
                GridLayoutManager(activity, LANDSCAPE_ORIENTATION_CELLS, LinearLayoutManager.VERTICAL, false)
            }

        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        cellAdapter = CellAdapter(data!!, CellClickHandler())
        recyclerView?.adapter = cellAdapter

        val button: Button = view.findViewById(R.id.containedButton) as Button
        button.setOnClickListener {
            cellAdapter?.addCell()
            count++
            cellAdapter?.notifyDataSetChanged()
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNT_KEY, count)
    }

    private fun initializeData(count: Int): ArrayList<Cell> {
        val data = ArrayList<Cell>()
        for (position in 1..count) {
            val cell = Cell(position, getColor(position))
            data.add(cell)
        }
        return data
    }

    inner class CellClickHandler : CellViewHolder.IListener {
        override fun onCellClicked(position: Int) {
            val cell = Cell(position, getColor(position))
            listener?.onCellClicked(cell)
            val numberFragment = NumberFragment()
            val bundle = Bundle()
            bundle.putInt(POSITION_KEY, position)
            numberFragment.arguments = bundle
            val manager: FragmentManager = childFragmentManager
            manager.beginTransaction()
                .replace(R.id.container, numberFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
