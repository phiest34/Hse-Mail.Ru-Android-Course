package com.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


open class MainActivity : AppCompatActivity(), CellsFragment.IListener {

    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = CellsFragment()
            manager.beginTransaction()
                .add(R.id.data, fragment)
                .commit()

        }
    }

    override fun onCellClicked(position: Int) {
        val numberFragment = NumberFragment()
        val bundle = Bundle()
        bundle.putInt(CellsFragment.POSITION_KEY, position + 1)
        numberFragment.arguments = bundle
        manager.beginTransaction()
            .replace(R.id.data, numberFragment)
            .addToBackStack(null)
            .commit()
    }
}
