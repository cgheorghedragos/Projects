package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.settings.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemClickString

class UpdateDialogWithOneString constructor(
    private val description: String,
    private val oldValue: String,
    private val listener: ItemClickString
) : DialogFragment() {
    private lateinit var text: TextView
    private lateinit var newValue: TextView
    private lateinit var confirmBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_update_with_one_string, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initListeners() {
        confirmBtn.setOnClickListener {
            listener.onClick(newValue.text.toString())
        }
    }

    private fun initViews(view: View) {
        text = view.findViewById(R.id.old_value_dialog)
        newValue = view.findViewById(R.id.new_value_dialog)
        confirmBtn = view.findViewById(R.id.confirm_new_value_dialog)

        text.text = description
        newValue.text = oldValue
    }

}