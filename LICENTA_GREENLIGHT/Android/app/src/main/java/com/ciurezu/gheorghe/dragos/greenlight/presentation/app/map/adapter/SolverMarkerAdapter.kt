package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R

class SolverMarkerAdapter(
    private var users: ArrayList<String> = ArrayList()
) :
    RecyclerView.Adapter<SolverMarkerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val deleteIcon: ImageView = itemView.findViewById(R.id.delete_icon)
        private val usernameText: TextView = itemView.findViewById(R.id.username_item_people)

        fun updateData(data: String) {
            usernameText.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_people_added_solved, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: String = users[position]

        holder.deleteIcon.setOnClickListener {
            val pos: Int = holder.bindingAdapterPosition

            users.removeAt(pos)
            notifyItemRemoved(pos)
        }
        holder.updateData(data)

    }

    fun updateListAdapter(data: ArrayList<String>) {
        users = data
        notifyDataSetChanged()
    }

    fun addUser(data: String) {
        if (!users.contains(data)) {
            users.add(data);
            notifyItemInserted(users.size - 1)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun getUsers(): ArrayList<String> {
        return users
    }
}