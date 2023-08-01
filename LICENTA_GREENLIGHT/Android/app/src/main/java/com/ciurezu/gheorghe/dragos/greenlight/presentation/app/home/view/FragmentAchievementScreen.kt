package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement.ActiveAchResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement.InactiveAchResponse
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.adapter.AchievementAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel.AchievementViewModel
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel.HomeScreenViewModel
import dagger.android.support.AndroidSupportInjection
import java.lang.StringBuilder
import java.util.stream.Collectors
import javax.inject.Inject


class FragmentAchievementScreen : Fragment() {
    @Inject
    lateinit var achievementVM: AchievementViewModel

    private lateinit var closeBtn: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var inactiveRV: RecyclerView
    private lateinit var achAdapter: AchievementAdapter<ActiveAchResponse>
    private lateinit var iAchAdapter: AchievementAdapter<InactiveAchResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_achievement_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
        setupObservers()
        achievementVM.getAllAchievements()
    }

    private fun setupObservers() {
        setupAchActiveObserver()
        setupIAchActiveObserver()
    }

    private fun setupIAchActiveObserver() {
        achievementVM.achInactiveResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    iAchAdapter.updateListAdapter(it.data.payload)
                    achievementVM.achInactiveResponse.postValue(null)
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "An error has ocurred", Toast.LENGTH_LONG)
                        .show()
                    achievementVM.achInactiveResponse.postValue(null)
                }

            }
        }
    }

    private fun setupAchActiveObserver() {
        achievementVM.achActiveResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    achAdapter.updateListAdapter(it.data.payload)
                    achievementVM.achActiveResponse.postValue(null)
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "An error has ocurred", Toast.LENGTH_LONG)
                        .show()
                    achievementVM.achActiveResponse.postValue(null)
                }
            }
        }
    }

    private fun initListeners() {
        closeBtn.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_fragmentAchievementScreen_to_fragmentHomeScreen)
        }
    }

    private fun initViews(view: View) {
        closeBtn = view.findViewById(R.id.close_button_achievemetn)
        recyclerView = view.findViewById(R.id.in_progress_rv)
        recyclerView.layoutManager = GridLayoutManager(view.context, 4)

        inactiveRV = view.findViewById(R.id.completed_rv)
        inactiveRV.layoutManager = GridLayoutManager(view.context, 3)
        achAdapter =
            AchievementAdapter<ActiveAchResponse>(
                supportFragmentManager = requireActivity().supportFragmentManager
            )
        iAchAdapter = AchievementAdapter<InactiveAchResponse>(
            supportFragmentManager = requireActivity().supportFragmentManager
        )

        recyclerView.adapter = achAdapter
        inactiveRV.adapter = iAchAdapter
    }
}