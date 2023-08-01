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
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.OnClickBuy
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.OnVoucherClick
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.adapter.ShoppingAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.dialog.BuyDialog
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.viewmodel.ShoppingViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FragmentShoppingBuy : Fragment() {
    @Inject
    lateinit var shoppingVM: ShoppingViewModel

    lateinit var shoppingRV: RecyclerView
    lateinit var shoppingAdp: ShoppingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_buy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners(view)

        shoppingVM.fetchVouchers()
    }

    private fun setupListeners(view: View) {
        setupObservers()
    }

    private fun setupObservers() {
        setupVoucherObserver()
    }

    private fun setupVoucherObserver() {
        shoppingVM.fetchVouchersResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    shoppingAdp.updateListAdapter(it.data.payload)
                }

                is Resource.Error -> {
                }

                is Resource.Loading -> {
                }
            }

        }
    }

    private fun setupViews(view: View) {
        shoppingRV = view.findViewById(R.id.shopping_buy_rv)
        shoppingAdp = ShoppingAdapter(listener = onClick)
        shoppingRV.layoutManager = LinearLayoutManager(context)
        shoppingRV.adapter = shoppingAdp
    }

    private val onClick = object : OnVoucherClick {
        override fun onClick(voucher: Voucher) {
            val dialogFragment = BuyDialog(listener = onBuyClick , voucher = voucher)
            dialogFragment.show(requireActivity().supportFragmentManager, "buy_fragment")
        }
    }

    private val onBuyClick = object: OnClickBuy{
        override fun voucher(voucher: Voucher, quantity: Int) {
            shoppingVM.buyVoucher(voucher,quantity)
        }

    }
}