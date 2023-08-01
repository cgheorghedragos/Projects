package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import java.io.File

class ImageFileAdapter(private var files: List<File> = ArrayList()) :
    RecyclerView.Adapter<ImageFileAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.photo_image_view_list)

        fun updateData(file: File) {
            val filePath = file.absolutePath
            val bitmap = BitmapFactory.decodeFile(filePath)

            image.setImageBitmap(bitmap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: File = files[position]

        holder.updateData(data)
    }

    fun updateListAdapter(fileList: List<File>) {
        files = fileList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return files.size
    }

    fun getFiles() : List<File>{
        return files
    }
}