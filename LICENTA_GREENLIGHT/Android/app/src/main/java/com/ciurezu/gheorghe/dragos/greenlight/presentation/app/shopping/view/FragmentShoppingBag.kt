package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.Voucher
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.OnVoucherClick
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.adapter.MyVoucherAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.dialog.BuyDialog
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.viewmodel.ShoppingViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FragmentShoppingBag : Fragment() {
    @Inject
    lateinit var shoppingVM: ShoppingViewModel

    private lateinit var voucherViewRV: RecyclerView
    private lateinit var voucherAdp: MyVoucherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_bag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
        setupListeners(view)

        shoppingVM.getUserVouchers()
    }

    private fun setupListeners(view: View) {
        setupObservers()
    }

    private fun setupObservers() {
        setupVoucherObserver()
    }

    private fun setupVoucherObserver() {
        shoppingVM.fetchUserVoucherResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    voucherAdp.updateListAdapter(it.data.payload)
                }

                is Resource.Error -> {
                }

                is Resource.Loading -> {
                }
            }

        }
    }

    private fun setupViews(view: View) {
        voucherViewRV = view.findViewById(R.id.shopping_view_rv)
        voucherAdp = MyVoucherAdapter()
        voucherViewRV.layoutManager = LinearLayoutManager(context)
        voucherViewRV.adapter = voucherAdp
    }

}