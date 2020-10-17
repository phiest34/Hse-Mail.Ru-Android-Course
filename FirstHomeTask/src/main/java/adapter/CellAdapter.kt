package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.CellFragment
import com.example.ui.R
import objects.Cell
import timber.log.Timber


class CellAdapter(var arrayList: ArrayList<Cell>) :
    RecyclerView.Adapter<CellViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val holder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return CellViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val cell: Cell = arrayList[position]
        holder.number.text = cell.number.toString()

        holder.number.setTextColor(cell.color)
        holder.number.setOnClickListener{
            Timber.i("number ${holder.number.text} clicked!")
        }
    }

    fun addCell() {
        val newCell = Cell(itemCount + 1, CellFragment.getColor(itemCount + 1))
        arrayList.add(newCell)
    }
}
