package adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R
import objects.Cell

class CellViewHolder(itemView: View, val listener: IListener?) : RecyclerView.ViewHolder(itemView) {
    val number: TextView = itemView.findViewById(R.id.number)
    val card: CardView = itemView.findViewById(R.id.card)
    val button: Button? = itemView.findViewById(R.id.containedButton)

    interface IListener {
        fun onCellClicked(position: Int)
    }

    init {
        itemView.setOnClickListener {
            listener?.onCellClicked(adapterPosition)
        }
    }
    fun bind(cell: Cell, position: Int) {
        number.text = cell.number.toString()
        number.setTextColor(cell.color)
    }
}
