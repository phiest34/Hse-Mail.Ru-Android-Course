package adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R

class CellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var number: TextView = itemView.findViewById(R.id.number)
}

