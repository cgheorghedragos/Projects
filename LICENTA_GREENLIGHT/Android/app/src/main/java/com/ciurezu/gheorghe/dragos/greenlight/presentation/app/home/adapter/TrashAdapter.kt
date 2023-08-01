package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.TrashResponse
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.BarcodeClickListener
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemClickTwoString

class TrashAdapter<T>(
    private var list: List<T> = ArrayList(),
    private var isAdmin: Boolean,
    private var barcodeClickListener: BarcodeClickListener,
) : RecyclerView.Adapter<TrashAdapter<T>.ViewHolder>() {

    private var filteredList: List<T> = list

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trashCategory: TextView =
            itemView.findViewById(R.id.item_category_info)
        private val trashBarcode: TextView = itemView.findViewById(R.id.item_barcode_info)
        private val editButton: ImageView = itemView.findViewById(R.id.update_barcode)
        private val deleteButton: ImageView = itemView.findViewById(R.id.delete_barcode)


        fun updateData(data: T) {
            if (data is TrashResponse) {
                trashBarcode.text = data.barcode
                trashCategory.text = data.categoryName

                editButton.setOnClickListener {
                    barcodeClickListener.onEditClick(data)
                }

                deleteButton.setOnClickListener {
                    barcodeClickListener.onDeleteClick(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_trash, parent, false)
        view.findViewById<ImageView>(R.id.update_barcode).visibility =
            if (isAdmin) View.VISIBLE else View.GONE
        view.findViewById<ImageView>(R.id.delete_barcode).visibility =
            if (isAdmin) View.VISIBLE else View.GONE
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = filteredList[position]

        holder.updateData(data)

    }

    fun updateListAdapter(newList: List<T>) {
        list = newList
        filteredList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            list
        } else {
            list.filter {
                if (it is TrashResponse) {
                    it.barcode.contains(query.toString())
                } else false
            }
        }
        notifyDataSetChanged()
    }

}