package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.TrashResponse
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemClickTwoString

class UpdateBarCodeDialog constructor(
    private val trashResponse: TrashResponse,
    private val updateBarcodeListener: ItemClickTwoString
) :
    DialogFragment() {
    private lateinit var oldBarcode: TextView
    private lateinit var categorySpinner: Spinner
    private lateinit var confirmButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_update_barcode, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        oldBarcode = view.findViewById(R.id.update_old_barcode)
        categorySpinner = view.findViewById(R.id.update_spinner_value)
        confirmButton = view.findViewById(R.id.confirm_update_barcode)

        oldBarcode.text = StringBuilder("Old Barcode:  $trashResponse").toString()
        val aAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this.requireContext(), R.array.categories,
            com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item
        )
        categorySpinner.adapter = aAdapter
        val defaultCategory = aAdapter.getPosition(trashResponse.categoryName)
        categorySpinner.setSelection(defaultCategory)

        confirmButton.setOnClickListener {
            updateBarcodeListener.onClick(
                secondValue = trashResponse.barcode.toString(),
                firstValue = categorySpinner.selectedItem.toString()
            )
            dismiss()
        }
    }
}