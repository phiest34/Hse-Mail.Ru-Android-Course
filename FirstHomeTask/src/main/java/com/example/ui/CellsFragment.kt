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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import objects.Cell

class CellsFragment : Fragment() {
    companion object {
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

    private var cellFragmentView: Fragment? = null

    private var recyclerView: RecyclerView? = null

    private var gridLayoutManager: GridLayoutManager? = null

    private var data: ArrayList<Cell>? = null

    private var cellAdapter: CellAdapter? = null

    private var count: Int = 100

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
            count = savedInstanceState.getInt("count")
            data = initializeData(count)
        }

        cellFragmentView = view.findViewById(R.id.cell_fragment)
        recyclerView = view.findViewById(R.id.recycler_view_item)
        gridLayoutManager =
            if (resources.configuration.orientation == SCREEN_ORIENTATION_PORTRAIT) {
                GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
            } else {
                GridLayoutManager(activity, 4, LinearLayoutManager.VERTICAL, false)
            }

        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        cellAdapter = CellAdapter(data!!, CellClickHandler())
        recyclerView?.adapter = cellAdapter

        val button: Button = view.findViewById(R.id.containedButton) as Button
        button.setOnClickListener {
            Toast.makeText(context, "The item was added", Toast.LENGTH_LONG).show()
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

    inner class CellClickHandler : CellViewHolder.IListener {
        override fun onCellClicked(position: Int) {
            val cell = Cell(position, getColor(position))
            listener?.onCellClicked(cell)
            val numberFragment = NumberFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            numberFragment.arguments = bundle
            val manager: FragmentManager = childFragmentManager
            Toast.makeText(context, "Cell ${position + 1} clicked!", Toast.LENGTH_SHORT).show()
            manager.beginTransaction()
                .replace(R.id.container, numberFragment)
                .addToBackStack(null)
                .commit()
        }
    }


}
