package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemTwoIntOneString

class AddVoucherDialog constructor(val itemTwoIntOneString: ItemTwoIntOneString) :
    DialogFragment() {
    private lateinit var quantity: EditText
    private lateinit var price: EditText
    private lateinit var confirmBtn: Button
    private lateinit var description: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_add_voucher, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initListener()
    }

    private fun initListener() {
        confirmBtn.setOnClickListener {
            if (validateInputs()) {
                itemTwoIntOneString.onClick(
                    price.text.toString().toInt(),
                    quantity.text.toString().toInt(),
                    description.text.toString()
                )
                Log.e("AAAA","VALIDAT")
            }
        }
    }

    private fun initViews(view: View) {
        quantity = view.findViewById(R.id.quantity)
        price = view.findViewById(R.id.price)
        description = view.findViewById(R.id.voucher_description)
        confirmBtn = view.findViewById(R.id.add_new_voucher)
    }

    private fun validateInputs(): Boolean {
        var found = true
        try {
            if (quantity.text.toString().toInt() < 0) {
                found = false
                quantity.error = "Please set a valid quantity"
            } else {
                quantity.error = null
            }

            if (price.text.toString().toInt() < 0) {
                found = false
                price.error = "Please set a valid price"
            } else {
                price.error = null
            }

            if (description.length() < 3) {
                found = false
                description.error = "Please set a valid quantity"
            } else {
                description.error = null
            }
        } catch (nr: NumberFormatException) {
            found = false
            Toast.makeText(context, "Set a valid number", Toast.LENGTH_LONG).show()
        }

        return found

    }

}