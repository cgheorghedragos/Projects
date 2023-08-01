package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.adapter.ImageFileAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.viewmodel.MapViewModel
import com.ciurezu.gheorghe.dragos.greenlight.util.FileUploadUtil
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FragmentReportIncident : Fragment() {
    @Inject
    lateinit var mapVM: MapViewModel

    private lateinit var declineButton: CardView
    private lateinit var acceptButton: CardView
    private lateinit var imageView: ImageView
    private lateinit var addPhotos: ImageView
    private lateinit var photoRV: RecyclerView
    private lateinit var photoAdapter: ImageFileAdapter
    private lateinit var descriptionText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_incident, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners(view)
    }

    private fun initListeners(view: View) {
        declineButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentReportIncident_to_fragmentGoogleMap)
        }

        addPhotos.setOnClickListener {
            if (CheckStorage.isReadExternalStoragePermissionGranted(it.context)) {
                pickImage()
            } else {
                requestReadExternalStoragePermission()
            }
        }

        acceptButton.setOnClickListener {
            mapVM.uploadImage(
                photoAdapter.getFiles(), requireContext(), descriptionText.text.toString()
            )

            it.findNavController().navigate(R.id.action_fragmentReportIncident_to_fragmentGoogleMap)
        }
    }

    private fun initViews(view: View) {

        declineButton = view.findViewById(R.id.report_incident_decline)
        acceptButton = view.findViewById(R.id.report_incident_aprove)
        imageView = view.findViewById(R.id.image_view_rep_incident)
        addPhotos = view.findViewById(R.id.add_photos)
        descriptionText = view.findViewById(R.id.report_incident_editText_info)
        photoRV = view.findViewById(R.id.photo_incident_rv)

        imageView.setImageResource(mapVM.currentDataSelected.icon)

        photoAdapter = ImageFileAdapter()
        photoRV.layoutManager = LinearLayoutManager(view.context)
        photoRV.adapter = photoAdapter
    }

    private fun requestReadExternalStoragePermission() {
        CheckStorage.requestReadExternalStoragePermission(requireActivity())
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(intent, 3)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 3 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val fileList = FileUploadUtil.getFileListFromUri(data, requireContext())

                photoAdapter.updateListAdapter(fileList)
            }

        }
    }
}