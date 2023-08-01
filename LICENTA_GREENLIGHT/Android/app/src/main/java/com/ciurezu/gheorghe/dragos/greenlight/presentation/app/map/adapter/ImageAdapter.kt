package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ciurezu.gheorghe.dragos.greenlight.R
import java.io.File

class ImageAdapter (private var images: List<String> = ArrayList()) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.photo_image_view_list)

        fun updateData(imagePath: String) {

            Glide.with(itemView.context).load(imagePath).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: String = images[position]

        holder.updateData(data)
    }

    fun updateListAdapter(imagesList: List<String>) {
        images = imagesList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return images.size
    }

}