package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.Voucher
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.OnClickBuy
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.viewmodel.ShoppingViewModel

class BuyDialog constructor(
    private val listener: OnClickBuy,
    private val voucher: Voucher
) : DialogFragment() {
    private lateinit var incrementBtn: Button
    private lateinit var decrementBtn: Button
    private lateinit var confirmBtn: ImageView
    private lateinit var quantity: TextView
    private  var currentSelectedQuantity: Int = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_buy_item, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()

    }

    private fun initViews(view: View) {
        decrementBtn = view.findViewById(R.id.down_the_number_of_vouchers)
        incrementBtn = view.findViewById(R.id.add_number_of_vouchers)
        confirmBtn = view.findViewById(R.id.confirm_button_to_buy_vouchers)
        quantity = view.findViewById(R.id.textView6)
        quantity.text = currentSelectedQuantity.toString()
    }

    private fun initListeners() {
        decrementBtn.setOnClickListener {
            currentSelectedQuantity -= 1;
            quantity.text = currentSelectedQuantity.toString()
            checkInputs(currentSelectedQuantity)
        }

        incrementBtn.setOnClickListener {
            currentSelectedQuantity += 1;
            quantity.text = currentSelectedQuantity.toString()
            checkInputs(currentSelectedQuantity)
        }
        confirmBtn.setOnClickListener {
            listener.voucher(voucher,currentSelectedQuantity)
            dismiss()
        }
    }

    private fun checkInputs(value: Int) {
        if (currentSelectedQuantity == 0) {
            decrementBtn.isEnabled = false
            confirmBtn.isEnabled = false
        } else if (currentSelectedQuantity > 0 && currentSelectedQuantity < voucher.quantity) {
            decrementBtn.isEnabled = true
            incrementBtn.isEnabled = true
            confirmBtn.isEnabled = true

        } else {
            incrementBtn.isEnabled = false
        }
    }
}