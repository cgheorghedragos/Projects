package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.Voucher
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.OnVoucherClick

class ShoppingAdapter(
    private var voucherList: List<Voucher> = ArrayList(),
    private var listener: OnVoucherClick
) :
    RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val companyText: TextView = itemView.findViewById(R.id.text_discount_company)
        private val descriptionText: TextView =
            itemView.findViewById(R.id.description_buy_text_view)
        private val quantityText: TextView = itemView.findViewById(R.id.item_quantity_to_buy)
        private val priceText: TextView = itemView.findViewById(R.id.item_price_to_buy)

        fun updateData(data: Voucher) {
            companyText.text = data.company.name
            descriptionText.text = data.description
            quantityText.text = data.quantity.toString()
            priceText.text = StringBuilder(data.price.toString() + " $").toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_buy_voucher, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val voucher = voucherList[position]

        holder.updateData(voucher)
        holder.itemView.setOnClickListener {
            listener.onClick(voucher)
        }
    }

    fun updateListAdapter(vouchers: List<Voucher>) {
        voucherList = vouchers
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return voucherList.size
    }
}