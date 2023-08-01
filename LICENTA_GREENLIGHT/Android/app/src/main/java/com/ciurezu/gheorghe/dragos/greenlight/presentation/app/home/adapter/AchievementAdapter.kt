package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement.ActiveAchResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement.InactiveAchResponse
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog.AchievementInfoDialog
import com.google.android.material.progressindicator.LinearProgressIndicator

class AchievementAdapter<T>(
    private var achievements: List<T> = ArrayList(),
    private var supportFragmentManager: FragmentManager
) :
    RecyclerView.Adapter<AchievementAdapter<T>.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val achImage: ImageView = itemView.findViewById(R.id.achievement_image)
        private val progressIndicator: LinearProgressIndicator =
            itemView.findViewById(R.id.item_achievement_progress)


        fun updateData(data: T) {
            when (data) {
                is ActiveAchResponse -> {
                    progressIndicator.progress =
                        (data.currentProgress * 100 / data.achievementLvl.maxPoints).toInt()
                    Glide
                        .with(itemView.context)
                        .load(data.achievementLvl.imgLink)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .into(achImage);
                }

                is InactiveAchResponse -> {
                    progressIndicator.visibility = View.GONE
                    Glide
                        .with(itemView.context)
                        .load(data.achievementLvl.imgLink)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .into(achImage);
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_achievement, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: T = achievements[position]

        holder.updateData(data)
        holder.itemView.setOnClickListener {
            AchievementInfoDialog(data).show(
                supportFragmentManager,
                "Achievement_Dialog"
            )
        }
    }

    fun updateListAdapter(updatedDataList: List<T>) {
        achievements = updatedDataList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return achievements.size
    }
}