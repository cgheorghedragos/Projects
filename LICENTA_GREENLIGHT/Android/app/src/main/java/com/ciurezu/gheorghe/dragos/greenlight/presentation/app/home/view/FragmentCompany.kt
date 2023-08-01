package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_company.CompanyResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.Voucher
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.adapter.CompanyAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog.AddCompanyDialog
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel.CompanyViewModel
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.CompanyClickListener
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemTwoStringOneLong
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.settings.dialog.UpdateDialogWithTwoString
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FragmentCompany : Fragment() {
    @Inject
    lateinit var companyVM: CompanyViewModel

    private lateinit var closeFragmentBtn: TextView
    private lateinit var addNewCompany: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var companyAdapter: CompanyAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_f_a_q_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initListeners(view)
        initObservers()

        companyVM.getCompanies()
    }

    private fun initObservers() {
        companyVM.successResponse.observe(viewLifecycleOwner) {
            if (it == true) {
                Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                companyVM.successResponse.postValue(false)
            }
        }

        companyVM.errorResponse.observe(viewLifecycleOwner) {
            if (it.length > 2) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                companyVM.errorResponse.postValue("")
            }
        }

        companyVM.companiesResponse.observe(viewLifecycleOwner) {
            companyAdapter.updateListAdapter(it)
        }
    }

    private fun initViews(view: View) {
        closeFragmentBtn = view.findViewById(R.id.close_faq_screen)
        searchView = view.findViewById(R.id.search_faq)
        addNewCompany = view.findViewById(R.id.add_new_faq)
        addNewCompany.setText(R.string.add_new_company)
        initRV(view)
    }

    private fun initRV(view: View) {
        recyclerView = view.findViewById(R.id.rv_faq)
        companyAdapter = CompanyAdapter(listener = companyClickListener)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = companyAdapter
    }

    private fun initListeners(view: View) {
        searchView.setOnQueryTextListener(queryTextListener)
        closeFragmentBtn.setOnClickListener { findNavController().navigate(R.id.action_companyFragment_to_fragmentHomeScreen) }

        addNewCompany.setOnClickListener {
            AddCompanyDialog(companyVM)
                .show(requireActivity().supportFragmentManager, "add_company_dialog")
        }

    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            companyAdapter.filter(newText)
            return true
        }
    }

    private var companyClickListener = object : CompanyClickListener {
        override fun onClick(companyResponse: CompanyResponse) {
            UpdateDialogWithTwoString(
                companyResponse.id,
                "Update the company",
                companyResponse.name,
                companyResponse.username,
                companyClickEdit
            ).show(requireActivity().supportFragmentManager,
                "update_company")
        }
    }

    private var companyClickEdit = object : ItemTwoStringOneLong {
        override fun onClick(firstValue: String, secondValue: String, id: Long) {
            val updatedCompany = CompanyResponse(
                id = id,
                name = firstValue,
                username = secondValue,
                ArrayList<Voucher>()
            )
            companyVM.updateCompany(updatedCompany)

        }

    }

}