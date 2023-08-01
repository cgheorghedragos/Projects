package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_company.CompanyResponse
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.CompanyClickListener

class CompanyAdapter(
    private var list: List<CompanyResponse> = ArrayList<CompanyResponse>(),
    private var listener: CompanyClickListener
) : RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {


    private var filteredList: List<CompanyResponse> = list

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val companyName: TextView = itemView.findViewById(R.id.item_company)
        private val username: TextView = itemView.findViewById(R.id.item_user)
        private val editButton: ImageView = itemView.findViewById(R.id.update_company)


        fun updateData(data: CompanyResponse) {
            companyName.text = data.name
            username.text = data.username

            editButton.setOnClickListener {
                listener.onClick(companyResponse = data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_company, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = filteredList[position]

        holder.updateData(data)
    }

    fun updateListAdapter(newList: List<CompanyResponse>) {
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
                it.name.contains(query.toString())
            }
        }
        notifyDataSetChanged()
    }
}
