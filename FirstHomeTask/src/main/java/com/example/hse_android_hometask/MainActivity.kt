package com.example.hse_android_hometask

import CellAdapter.CellAdapter
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import objects.Cell

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var data: ArrayList<Cell>? = null
    private var cellAdapter: CellAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        data = initializeData()
        recyclerView = findViewById(R.id.recycler_view_item)
        gridLayoutManager =
            GridLayoutManager(applicationContext, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        cellAdapter = CellAdapter(applicationContext, data!!)
        recyclerView?.adapter = cellAdapter

    }

    protected fun initializeData(): ArrayList<Cell> {
        val data = ArrayList<Cell>()
        for (position in 1..100) {
            val number = position + 1
            val color: Int
            if (number % 2 == 0) {
                color = RED
            } else {
                color = BLUE
            }
            val cell = Cell(number, color)
            data.add(cell)
        }
        return data
    }

}