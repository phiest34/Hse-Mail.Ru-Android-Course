package CellAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hse_android_hometask.R
import objects.Cell

class CellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var number = itemView.findViewById<TextView>(R.id.number)
    fun bind(itemView: Cell) {

    }
}
