package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.viewmodel.ShoppingViewModel
import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.adapter.ViewPagerIntroAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FragmentShoppingScreen : Fragment() {
    @Inject
    lateinit var shoppingVM: ShoppingViewModel


    private lateinit var shoppingTb: TabLayout
    private lateinit var shoppingVp: ViewPager2
    private lateinit var viewPagerIntroAdapter: ViewPagerIntroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListener()
    }

    private fun setupListener() {
        shoppingVM.buyVoucherResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    shoppingVM.getUserVouchers()
                    shoppingVM.fetchVouchers()
                }

                is Resource.Error -> {}
            }
        }
    }

    private fun setupViews(view: View) {

        shoppingTb = view.findViewById(R.id.shopping_tab_layout)
        shoppingVp = view.findViewById<ViewPager2>(R.id.shopping_vp)

        viewPagerIntroAdapter = ViewPagerIntroAdapter(childFragmentManager, lifecycle)
        viewPagerIntroAdapter.addFragments(FragmentShoppingBuy())
        viewPagerIntroAdapter.addFragments(FragmentShoppingBag())
        shoppingVp.adapter = viewPagerIntroAdapter

        TabLayoutMediator(shoppingTb, shoppingVp) { tab, position ->
            run {
                if (position == 0) tab.text = "Buy Vouchers" else tab.text = "My Vouchers"
            }
        }.attach()
    }
}