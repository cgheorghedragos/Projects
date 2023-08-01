package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.faq.SimpleFAQ
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel.FAQViewModel

class AddFaqDialog constructor(private val faqViewModel: FAQViewModel) : DialogFragment() {
    private lateinit var titleET: EditText
    private lateinit var descriptionET: EditText
    private lateinit var confirmBtn: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_add_faq, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initViews(view: View) {
        titleET = view.findViewById(R.id.title_add)
        descriptionET = view.findViewById(R.id.description_add)
        confirmBtn = view.findViewById(R.id.add_new_faq_btn)
    }

    private fun initListeners() {

        confirmBtn.setOnClickListener {
            val faq = SimpleFAQ(titleET.text.toString(), descriptionET.text.toString())
            faqViewModel.addFaq(faq)
        }
    }
}