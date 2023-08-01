package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.ranking.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_users_by_coins.response.GetAllUsersPctResponse

class RankingInfoAdapter constructor(private var rankingList: List<GetAllUsersPctResponse> = ArrayList()) :
    RecyclerView.Adapter<RankingInfoAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val position: TextView = itemView.findViewById(R.id.positionInClasamentText)
        private val imageView: ImageView = itemView.findViewById(R.id.rankingImageView)
        private val username: TextView = itemView.findViewById(R.id.usernameItemRanking)
        private val points: TextView = itemView.findViewById(R.id.pointsItemRanking)

        fun updateData(user: GetAllUsersPctResponse, iPosition: Int) {

            position.text = String.format((iPosition + 4).toString())
            username.text = user.username
            points.text = String.format(user.score.toString() + " pct")
            Glide
                .with(itemView.context)
                .load(user.photoUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(imageView);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ranking_person, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: GetAllUsersPctResponse = rankingList[position]

        holder.updateData(user, position)
    }

    fun updateListAdapter(ranks: List<GetAllUsersPctResponse>) {
        rankingList = ranks
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return rankingList.size
    }
}