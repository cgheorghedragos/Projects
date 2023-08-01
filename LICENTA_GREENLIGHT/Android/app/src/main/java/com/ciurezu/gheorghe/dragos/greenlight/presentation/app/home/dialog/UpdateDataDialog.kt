package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel.FAQViewModel
import java.lang.StringBuilder

class UpdateDataDialog constructor(
    private val viewModel: FAQViewModel,
    private val title: String,
    private val description: String
) : DialogFragment() {
    private lateinit var oldTitle: TextView
    private lateinit var oldDescription: TextView
    private lateinit var newTitle: EditText
    private lateinit var newDescription: EditText
    private lateinit var confirmButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_update_faq, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        oldTitle = view.findViewById(R.id.old_faq_name)
        oldDescription = view.findViewById(R.id.old_description)
        newTitle = view.findViewById(R.id.new_title_faq)
        newDescription = view.findViewById(R.id.new_description_faq)
        confirmButton = view.findViewById(R.id.confirm_update_faq)

        oldTitle.text = StringBuilder("Old title: $title").toString()
        oldDescription.text = StringBuilder("Old Description: $description").toString()
        newDescription.setText(description)
        newTitle.setText(title)

        confirmButton.setOnClickListener {
            viewModel.updateFaq(
                title,
                newTitle.text.toString(),
                newDescription.text.toString()
            )
            dismiss()
        }
    }
}