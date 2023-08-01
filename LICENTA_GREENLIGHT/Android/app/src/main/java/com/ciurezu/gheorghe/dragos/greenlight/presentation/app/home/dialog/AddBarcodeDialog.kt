package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemClickTwoString

class AddBarcodeDialog constructor(private var listener: ItemClickTwoString) : DialogFragment() {
    private lateinit var category: Spinner
    private lateinit var barcodeText: EditText
    private lateinit var confirmBtn: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_add_barcode, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
    }

    private fun initViews(view: View) {
        category = view.findViewById(R.id.category_spinner_add)
        val aAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this.requireContext(), R.array.categories,
            com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item
        )
        category.adapter = aAdapter
        barcodeText = view.findViewById(R.id.add_barcode_text)
        confirmBtn = view.findViewById(R.id.add_new_barcode)
    }

    private fun initListeners() {

        confirmBtn.setOnClickListener {
            val selectedCategory = category.selectedItem.toString()
            val barcode = barcodeText.text.toString()
            listener.onClick(selectedCategory, barcode)
        }
    }
}