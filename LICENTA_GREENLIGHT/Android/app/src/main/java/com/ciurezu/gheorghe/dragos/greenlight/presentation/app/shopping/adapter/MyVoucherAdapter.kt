package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.UserVoucher
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.Voucher
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.OnVoucherClick

class MyVoucherAdapter(
    private var voucherList: List<UserVoucher> = ArrayList()
) :
    RecyclerView.Adapter<MyVoucherAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val companyText: TextView = itemView.findViewById(R.id.item_text_discount_company_view)
        private val descriptionText: TextView =
            itemView.findViewById(R.id.description_bought_text_view)
        private val quantityText: TextView = itemView.findViewById(R.id.item_my_quantity)


        fun updateData(data: UserVoucher) {
            companyText.text = data.voucher.company.name
            descriptionText.text = data.voucher.description
            quantityText.text = data.quantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_voucher, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val voucher = voucherList[position]

        holder.updateData(voucher)
    }

    fun updateListAdapter(vouchers: List<UserVoucher>) {
        voucherList = vouchers
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return voucherList.size
    }
}