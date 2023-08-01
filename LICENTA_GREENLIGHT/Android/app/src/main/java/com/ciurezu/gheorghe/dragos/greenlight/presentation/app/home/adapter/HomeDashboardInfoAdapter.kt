package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.local.model.DashboardData
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.adapter.HomeDashboardItem

class HomeDashboardInfoAdapter(private var dashboardList: ArrayList<HomeDashboardItem> = ArrayList()) :
    RecyclerView.Adapter<HomeDashboardInfoAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.home_item_dashboard_image)
        private val text: TextView = itemView.findViewById(R.id.home_item_dashboard_description)

        fun updateData(data: HomeDashboardItem) {
            text.text = data.title
            image.setImageResource(data.icon)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: HomeDashboardItem = dashboardList[position]

        holder.updateData(data)
        holder.itemView.setOnClickListener{data.listener.onClickListener(holder.itemView)}
    }

    fun updateListAdapter(dd: List<DashboardData>) {
        // dashboardList = dd
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dashboardList.size
    }
}