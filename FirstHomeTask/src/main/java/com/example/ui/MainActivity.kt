package com.example.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

open class MainActivity : AppCompatActivity(), CellsFragment.IListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCellClicked(position: Int) {
        val numberFragment = NumberFragment()
        val bundle = Bundle()
        bundle.putInt(CellsFragment.POSITION_KEY, position)
        numberFragment.arguments = bundle
        val manager: FragmentManager = supportFragmentManager
        manager.beginTransaction()
            .replace(R.id.data, numberFragment)
            .addToBackStack(null)
            .commit()
    }
}
