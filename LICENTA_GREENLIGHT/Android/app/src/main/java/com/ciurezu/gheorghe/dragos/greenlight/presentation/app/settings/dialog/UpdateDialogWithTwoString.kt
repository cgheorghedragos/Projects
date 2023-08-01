package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.settings.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemTwoStringOneLong

class UpdateDialogWithTwoString constructor(
    private val id: Long,
    private val description: String,
    private val oldValue1: String,
    private val oldValue2: String,
    private val listener: ItemTwoStringOneLong
) : DialogFragment() {
    private lateinit var text: TextView
    private lateinit var newCompanyValue: EditText
    private lateinit var newUsernameValue: EditText
    private lateinit var confirmBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_update_with_two_string, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initListeners() {
        confirmBtn.setOnClickListener {
            listener.onClick(
                firstValue = newCompanyValue.text.toString(),
                secondValue = newUsernameValue.text.toString(),
                id = this@UpdateDialogWithTwoString.id
            )
            this@UpdateDialogWithTwoString.dismiss()
        }
    }

    private fun initViews(view: View) {
        text = view.findViewById(R.id.old_value_dialog)
        newCompanyValue = view.findViewById(R.id.new_company_value)
        newUsernameValue = view.findViewById(R.id.new_user_value)
        confirmBtn = view.findViewById(R.id.confirm_new_value_dialog)

        text.text = description
        newCompanyValue.setText(oldValue1)
        newUsernameValue.setText(oldValue2)
    }
}