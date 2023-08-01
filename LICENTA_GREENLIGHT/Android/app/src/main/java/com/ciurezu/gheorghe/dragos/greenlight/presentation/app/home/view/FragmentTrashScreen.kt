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
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.TrashResponse
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.adapter.TrashAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog.AddBarcodeDialog
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog.UpdateBarCodeDialog
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel.TrashViewModel
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.BarcodeClickListener
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemClickTwoString
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FragmentTrashScreen : Fragment() {
    @Inject
    lateinit var trashVM: TrashViewModel

    private lateinit var closeFragmentBtn: TextView
    private lateinit var addNewBarcode: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var trashAdapter: TrashAdapter<TrashResponse>
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

        trashVM.getAllTrashes()
    }

    private fun initObservers() {
        trashVM.allTrashResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    trashAdapter.updateListAdapter(it.data.payload)
                    trashVM.allTrashResponse.postValue(null)
                }

                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        it.errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                    trashVM.allTrashResponse.postValue(null)
                }
            }
        }

        trashVM.stringResponse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_LONG).show()
        }
    }


    private fun initViews(view: View) {
        closeFragmentBtn = view.findViewById(R.id.close_faq_screen)
        searchView = view.findViewById(R.id.search_faq)
        addNewBarcode = view.findViewById(R.id.add_new_faq)
        addNewBarcode.setText(R.string.add_new_barcode)
        initRV(view)

        addNewBarcode.visibility = if (trashVM.isAdmin()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun initRV(view: View) {
        recyclerView = view.findViewById(R.id.rv_faq)
        trashAdapter =
            TrashAdapter(
                isAdmin = trashVM.isAdmin(),
                barcodeClickListener = barcodeClickListener
            )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = trashAdapter
    }

    private fun initListeners(view: View) {
        searchView.setOnQueryTextListener(queryTextListener)
        addNewBarcode.setOnClickListener {
            AddBarcodeDialog(addBarcodeListener)
                .show(
                    requireActivity().supportFragmentManager,
                    "add_barcode"
                )
        }
        closeFragmentBtn.setOnClickListener { findNavController().navigate(R.id.action_fragmentTrashScreen_to_fragmentHomeScreen) }
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            trashAdapter.filter(newText)
            return true
        }
    }

    private var addBarcodeListener = object : ItemClickTwoString {
        override fun onClick(firstValue: String, secondValue: String) {
            //first = category, second = barcode
            val trash = TrashResponse(categoryName = firstValue, barcode = secondValue)
            trashVM.addTrash(trash)
        }
    }

    private var updateBarcodeListener = object : ItemClickTwoString {
        override fun onClick(firstValue: String, secondValue: String) {
            //first = category, second = barcode
            val trash = TrashResponse(categoryName = firstValue, barcode = secondValue)
            trashVM.updateTrash(trash)
        }
    }

    private var barcodeClickListener = object : BarcodeClickListener {
        override fun onEditClick(trashResponse: TrashResponse) {
            UpdateBarCodeDialog(trashResponse,updateBarcodeListener).show(requireActivity().supportFragmentManager,"update_barcode")
        }

        override fun onDeleteClick(trashResponse: TrashResponse) {
            trashVM.deleteTrash(trashResponse.barcode)
        }

    }
}