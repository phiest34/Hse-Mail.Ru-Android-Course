package CellAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hse_android_hometask.R
import objects.Cell


class CellAdapter(var context: Context, var arrayList: ArrayList<Cell>) :
    RecyclerView.Adapter<CellViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val holder = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return CellViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val cell: Cell = arrayList[position]
        holder.number.text = cell.number.toString()
    }


}
