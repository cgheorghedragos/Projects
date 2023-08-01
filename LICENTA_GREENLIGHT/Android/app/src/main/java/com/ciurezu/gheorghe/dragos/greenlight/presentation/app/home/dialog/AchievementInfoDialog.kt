package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement.ActiveAchResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement.InactiveAchResponse
import com.google.android.material.progressindicator.LinearProgressIndicator
import java.lang.StringBuilder

class AchievementInfoDialog<T> constructor(
    private val achievement: T,
) : DialogFragment() {
    private lateinit var achievementImage: ImageView
    private lateinit var name: TextView
    private lateinit var completed: TextView
    private lateinit var level: TextView
    private lateinit var category: TextView
    private lateinit var currentProgress: TextView
    private lateinit var indicator: LinearProgressIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_achievement, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
    }

    private fun initViews(view: View) {
        achievementImage = view.findViewById(R.id.achievement_image_info)
        name = view.findViewById(R.id.name_info)
        category = view.findViewById(R.id.category_info)
        level = view.findViewById(R.id.level_info)
        completed = view.findViewById(R.id.date_achieved)
        currentProgress = view.findViewById(R.id.current_progress_info)
        indicator = view.findViewById(R.id.current_progress_indicator)


        when (achievement) {
            is ActiveAchResponse -> {
                Glide.with(view.context).load(achievement.achievementLvl.imgLink).diskCacheStrategy(
                    DiskCacheStrategy.ALL).into(achievementImage)
                name.text = achievement.achievementLvl.achievementName
                category.text =
                    StringBuilder("Category: " + achievement.achievementLvl.category.name).toString()
                level.text = StringBuilder("Level: " + achievement.achievementLvl.level).toString()
                currentProgress.text =
                    StringBuilder("Current Progress: " + achievement.currentProgress + "/" + achievement.achievementLvl.maxPoints)
                completed.visibility = View.GONE
                indicator.progress =
                    (achievement.currentProgress * 100 / achievement.achievementLvl.maxPoints).toInt()
            }

            is InactiveAchResponse -> {
                Glide.with(view.context).load(achievement.achievementLvl.imgLink).diskCacheStrategy(
                    DiskCacheStrategy.ALL).into(achievementImage)
                name.text = achievement.achievementLvl.achievementName
                category.text =
                    StringBuilder("Category: " + achievement.achievementLvl.category.name).toString()
                level.text = StringBuilder("Level: " + achievement.achievementLvl.level).toString()
                currentProgress.visibility = View.GONE
                completed.visibility = View.VISIBLE
                indicator.visibility = View.GONE
                completed.text = StringBuffer("Date Completed : " + achievement.createdOn).toString()
            }

        }
    }
}