package com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.adapter.ViewPagerIntroAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.viewmodel.SignUpViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.AndroidSupportInjection
import java.io.File
import javax.inject.Inject

class FragmentIntro : Fragment() {

    lateinit var getStartedButton: ImageButton
    lateinit var buttomSheetDialog: BottomSheetDialog
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var viewPagerIntroAdapter: ViewPagerIntroAdapter

    @Inject
    lateinit var VM: SignUpViewModel

    companion object {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupViews(view)
        setupListeners()
    }

    private fun setupListeners() {
        getStartedButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentIntro_to_fragmentChooseSignInOrUp)
        }
    }





    private fun setupViews(view: View) {
        buttomSheetDialog = BottomSheetDialog(view.context)
        getStartedButton = view.findViewById(R.id.getStartedButton)
        tabLayout = view.findViewById(R.id.intro_tab_layout)
        viewPager2 = view.findViewById<ViewPager2>(R.id.intro_vp)

        viewPagerIntroAdapter = ViewPagerIntroAdapter(childFragmentManager, lifecycle)
        viewPagerIntroAdapter.addFragments(FragmentTab(getString(R.string.save_planet_string),R.mipmap.ic_planet_layer))
        viewPagerIntroAdapter.addFragments(FragmentTab(getString(R.string.recycle_to_help_us_you_will_receive_virtual_money),R.drawable.recycle))
        viewPagerIntroAdapter.addFragments(FragmentTab(getString(R.string.report_incident_to_the_map_or_find_these_recyclers_bin),R.drawable.intro_map))
        viewPager2.adapter = viewPagerIntroAdapter

        TabLayoutMediator(tabLayout, viewPager2) { _, _ ->
            {}
        }.attach()
    }


}