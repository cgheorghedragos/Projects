package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemOnClick
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.OnPosClick
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.adapter.MapInfoAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.viewmodel.MapViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FragmentReportDashboard : Fragment() {
    @Inject
    lateinit var mapVM: MapViewModel


    private lateinit var recycleView: RecyclerView
    private lateinit var incidentAdapter: MapInfoAdapter
    private lateinit var closeBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners(view)
    }

    private fun initListeners(view: View) {

        closeBtn.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_fragmentReportDashboard_to_fragmentGoogleMap)
        }
    }

    private fun initViews(view: View) {
        initRecycleView(view)
        closeBtn = view.findViewById<TextView>(R.id.close_incident_chooser)
    }


    private fun initRecycleView(view: View) {
        recycleView = view.findViewById<RecyclerView>(R.id.report_rv)
        incidentAdapter = MapInfoAdapter(onClickListener, mapVM.fetchDashboardData())
        recycleView.layoutManager = GridLayoutManager(requireContext(), 2)
        recycleView.adapter = incidentAdapter
    }

    var onClickListener: OnPosClick = object : OnPosClick {
        override fun onClick(position: Int) {
            val dashboardData = mapVM.fetchDashboardData()[position]
            mapVM.currentDataSelected = dashboardData
            findNavController().navigate(R.id.action_fragmentReportDashboard_to_fragmentReportIncident)
        }
    }
}