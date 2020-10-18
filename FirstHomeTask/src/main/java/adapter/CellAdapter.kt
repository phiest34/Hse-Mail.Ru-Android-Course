package adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.CellsFragment
import com.example.ui.R
import objects.Cell
import timber.log.Timber


class CellAdapter(var arrayList: ArrayList<Cell>, val listener: CellViewHolder.IListener) :
    RecyclerView.Adapter<CellViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val holder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return CellViewHolder(holder, listener)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val cell: Cell = arrayList[position]
        holder.bind(cell, position)
    }

    fun addCell() {
        val newCell = Cell(itemCount + 1, CellsFragment.getColor(itemCount + 1))
        arrayList.add(newCell)
    }
}
