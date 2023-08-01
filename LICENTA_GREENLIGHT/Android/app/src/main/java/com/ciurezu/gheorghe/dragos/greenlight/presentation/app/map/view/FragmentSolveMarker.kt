package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.adapter.SolverMarkerAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.viewmodel.MapViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FragmentSolveMarker : Fragment() {

    @Inject
    lateinit var mapVM: MapViewModel

    private lateinit var closeBtn: TextView
    private lateinit var addUserBtn: TextView
    private lateinit var editTextAddPerson: EditText
    private lateinit var userAddedRV: RecyclerView
    private lateinit var solveBtn: Button
    private lateinit var adapter: SolverMarkerAdapter

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
        return inflater.inflate(R.layout.fragment_solve_marker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        mapVM.errorResponses.observe(viewLifecycleOwner){
            if(it != null){
                Toast.makeText(context,it,Toast.LENGTH_LONG).show()
                mapVM.errorResponses.postValue(null)
            }
        }

        mapVM.usersToAdd.observe(viewLifecycleOwner){
            adapter.addUser(it)
        }
    }

    private fun initListeners() {
        closeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSolveMarker_to_fragmentGoogleMap)
        }
        addUserBtn.setOnClickListener {
            mapVM.checkUserAvailability(editTextAddPerson.text.toString())
            editTextAddPerson.text.clear()
        }

        solveBtn.setOnClickListener {
            mapVM.solveMarker(adapter.getUsers())
            findNavController().navigate(R.id.action_fragmentSolveMarker_to_fragmentGoogleMap)
        }
    }

    private fun initViews(view: View) {
        closeBtn = view.findViewById(R.id.closeSolveIncident)
        addUserBtn = view.findViewById(R.id.addUserTextView)
        editTextAddPerson = view.findViewById(R.id.editTextAddPerson)
        userAddedRV = view.findViewById(R.id.userAddedRecycleView)
        solveBtn = view.findViewById(R.id.solveBtn)

        adapter = SolverMarkerAdapter()
        userAddedRV.layoutManager = LinearLayoutManager(context)
        userAddedRV.adapter = adapter
    }
}