package adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R
import objects.Cell

class CellViewHolder(itemView: View, private val listener: IListener?) : RecyclerView.ViewHolder(itemView) {
    private val number: TextView = itemView.findViewById(R.id.number)

    interface IListener {
        fun onCellClicked(position: Int)
    }

    init {
        itemView.setOnClickListener {
            listener?.onCellClicked(adapterPosition)
        }
    }
    fun bind(cell: Cell) {
        number.text = cell.number.toString()
        number.setTextColor(cell.color)
    }
}
