package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.local.model.DashboardData
import com.ciurezu.gheorghe.dragos.greenlight.data.local.model.FaqData
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.adapter.FAQExpandableAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog.AddFaqDialog
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel.FAQViewModel
import dagger.android.support.AndroidSupportInjection
import java.util.stream.Collector
import java.util.stream.Collectors
import javax.inject.Inject

class FragmentFAQScreen : Fragment() {
    @Inject
    lateinit var faqVM: FAQViewModel

    private lateinit var closeFragmentBtn: TextView
    private lateinit var addNewFaq: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var faqAdapter: FAQExpandableAdapter
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

        faqVM.getAllFaq()
    }

    private fun initObservers() {
        initFaqObserver()
        initUpdateObserver()
        initDeleteObserver()
    }

    private fun initDeleteObserver() {
        faqVM.faqDeleteResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    faqVM.getAllFaq()
                    faqVM.faqUpdateResponse.postValue(null)
                }

                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        it.errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                    faqVM.faqUpdateResponse.postValue(null)
                }
            }
        }
    }

    private fun initFaqObserver() {
        faqVM.faqResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    val faqData = it.data.payload.stream().map { faqResponse ->
                        FaqData(faqResponse.title, faqResponse.description, false)
                    }.collect(Collectors.toList())
                    faqAdapter.updateListAdapter(faqData)
                }

                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        it.errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun initUpdateObserver() {
        faqVM.faqUpdateResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    faqVM.getAllFaq()
                    faqVM.faqUpdateResponse.postValue(null)
                }

                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        it.errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                    faqVM.faqUpdateResponse.postValue(null)
                }
            }
        }
    }


    private fun initViews(view: View) {
        closeFragmentBtn = view.findViewById(R.id.close_faq_screen)
        searchView = view.findViewById(R.id.search_faq)
        addNewFaq = view.findViewById(R.id.add_new_faq)
        initRV(view)

        addNewFaq.visibility = if (faqVM.isAdmin()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun initRV(view: View) {
        recyclerView = view.findViewById(R.id.rv_faq)
        faqAdapter = FAQExpandableAdapter(
            ArrayList(),
            faqVM.isAdmin(),
            faqVM,
            requireActivity().supportFragmentManager
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = faqAdapter
    }

    private fun initListeners(view: View) {
        searchView.setOnQueryTextListener(queryTextListener)
        addNewFaq.setOnClickListener {
            AddFaqDialog(faqVM)
                .show(
                    requireActivity().supportFragmentManager,
                    "add_faq_dialog"
                )
        }
        closeFragmentBtn.setOnClickListener { findNavController().navigate(R.id.action_fragmentFAQScreen_to_fragmentHomeScreen) }
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            faqAdapter.filter(newText)
            return true
        }
    }

    companion object {

    }
}