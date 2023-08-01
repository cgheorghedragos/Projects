package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.local.model.DashboardData
import com.ciurezu.gheorghe.dragos.greenlight.data.local.model.FaqData
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog.UpdateDataDialog
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel.FAQViewModel

class FAQExpandableAdapter(
    private var faqList: List<FaqData> = ArrayList(),
    private var isAdmin: Boolean,
    private var vm: FAQViewModel,
    private var supportFragmentManager: FragmentManager
) :
    RecyclerView.Adapter<FAQExpandableAdapter.ViewHolder>() {

    private var filteredList: List<FaqData> = faqList

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val descriptionCardView: CardView =
            itemView.findViewById(R.id.faq_description_item_cardview)
        private val title: TextView = itemView.findViewById(R.id.faq_title_item_text)
        private val description: TextView =
            itemView.findViewById(R.id.faq_description_item_text)
        private val editButton: ImageView = itemView.findViewById(R.id.update_faq_data)
        private val deleteButton: ImageView = itemView.findViewById(R.id.delete_faq_data)

        fun updateData(data: FaqData) {
            title.text = data.title
            description.text = data.description
            descriptionCardView.visibility = getVisibility(data.isExtended)
            editButton.visibility = getVisibility(isAdmin)
            deleteButton.visibility = getVisibility(isAdmin)

            editButton.setOnClickListener {

                UpdateDataDialog(vm, title.text.toString(), description.text.toString()).show(supportFragmentManager,"updateData")
            }

            deleteButton.setOnClickListener {
                vm.deleteFaq(title.text.toString())
            }
        }

        fun expand(data: FaqData) {
            data.isExtended = !data.isExtended
            descriptionCardView.visibility = getVisibility(data.isExtended)
        }

        fun getVisibility(state: Boolean): Int {
            return if (state) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_faq_question, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: FaqData = filteredList[position]

        holder.updateData(data)

        holder.itemView.setOnClickListener { holder.expand(data) }
    }

    fun updateListAdapter(newFaqList: List<FaqData>) {
        faqList = newFaqList
        filteredList = faqList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            faqList
        } else {
            faqList.filter { it.title.contains(query, true) }
        }

        notifyDataSetChanged()
    }
}