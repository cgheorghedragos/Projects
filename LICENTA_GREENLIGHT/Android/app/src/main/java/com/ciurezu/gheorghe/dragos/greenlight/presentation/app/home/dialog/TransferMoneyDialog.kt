package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemOneLongOneString

class TransferMoneyDialog constructor(private val listener: ItemOneLongOneString) :
    DialogFragment() {
    private lateinit var usernameT: EditText
    private lateinit var moneyT: EditText
    private lateinit var confirmBtn: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_transfer_money, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initViews(view: View) {
        usernameT = view.findViewById(R.id.username_transfer)
        moneyT = view.findViewById(R.id.money_to_transfer)
        confirmBtn = view.findViewById(R.id.transfer_money)
    }

    private fun initListeners() {

        confirmBtn.setOnClickListener {
            try {
                val username = usernameT.text.toString()
                val money = moneyT.text.toString().toLong()
                listener.onClick(username, money)

            } catch (n: NumberFormatException) {
                Toast.makeText(context, "Please set a valid number", Toast.LENGTH_LONG).show()
            }
        }
    }
}