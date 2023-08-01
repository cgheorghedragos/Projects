package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_company.AddCompanyRequest
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel.CompanyViewModel

class AddCompanyDialog(private val companyViewModel: CompanyViewModel) : DialogFragment() {
    private lateinit var companyET: EditText
    private lateinit var usernameET: EditText
    private lateinit var confirmBtn: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_add_company, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initViews(view: View) {
        companyET = view.findViewById(R.id.company_add)
        usernameET = view.findViewById(R.id.username_add)
        confirmBtn = view.findViewById(R.id.add_new_company)
    }

    private fun initListeners() {
        confirmBtn.setOnClickListener {
            val addCompanyRequest =
                AddCompanyRequest(companyET.text.toString(), usernameET.text.toString())
            companyViewModel.addCompany(addCompanyRequest)
        }}
}