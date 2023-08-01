package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.adapter.HomeDashboardItem
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.adapter.HomeDashboardInfoAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog.AddVoucherDialog
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.dialog.TransferMoneyDialog
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel.HomeScreenViewModel
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemClickEvent
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemOneLongOneString
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemTwoIntOneString
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FragmentHomeScreen : Fragment() {
    @Inject
    lateinit var homeVM: HomeScreenViewModel

    private lateinit var dashboardRV: RecyclerView
    private lateinit var dashboardRVAdapter: HomeDashboardInfoAdapter
    private lateinit var userLayout: ConstraintLayout
    private lateinit var userPhoto: ImageView
    private lateinit var coinsTextView: TextView
    private lateinit var usernameTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners(view)
        setupObservers()

        homeVM.getUser()
    }

    private fun setupObservers() {
        homeVM.userResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    coinsTextView.text = StringBuilder(it.data.payload.coins + " $").toString()
                    usernameTextView.text =
                        StringBuilder("Hello, " + it.data.payload.username).toString()
                    Glide.with(userLayout.context).load(it.data.payload.photoUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(userPhoto)
                    homeVM.userResponse.postValue(null)
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "An error has ocurred", Toast.LENGTH_LONG)
                        .show()
                    homeVM.userResponse.postValue(null)
                }
            }
        }
    }

    private fun setupViews(view: View) {
        dashboardRV = view.findViewById<RecyclerView>(R.id.home_rv)
        userLayout = view.findViewById<ConstraintLayout>(R.id.top_bar_include)
        coinsTextView = userLayout.findViewById<TextView>(R.id.current_coins_user)
        usernameTextView = userLayout.findViewById(R.id.name_app_bar_text_view)
        userPhoto = userLayout.findViewById(R.id.user_top_bar_photo)


        dashboardRVAdapter = HomeDashboardInfoAdapter(fetchData(view))
        dashboardRV.layoutManager = GridLayoutManager(context, 2)
        dashboardRV.adapter = dashboardRVAdapter
    }

    private fun setupListeners(view: View) {
        homeVM.messageResponse.observe(viewLifecycleOwner){
            homeVM.getUser()
        }
    }

    companion object {
        private const val SETTINGS_DESCRIPTION = "Settings"
        private const val RANKING_DESCRIPTION = "Ranking"
        private const val SHOPPING_DESCRIPTION = "Shopping "
        private const val ACHIEVEMENT_DESCRIPTION = "Achievement"
        private const val QNA_DESCRIPTION = "FAQ"
        private const val BARCODE_DESCRIPTION = "Barcode"
        private const val MAP_DESCRIPTION = "Map"
        private const val LOGOUT_DESCRIPTION = "Logout"
        private const val COMPANY_DESCRIPTION = "Company"
        private const val TRANSFER_MONEY_DESCRIPTION = "Transfer money"
    }


    private fun fetchData(fragmentView: View): ArrayList<HomeDashboardItem> {
        val shopping = if(homeVM.isShopper()){
            HomeDashboardItem(R.drawable.ic_shopping_cart_2,
                SHOPPING_DESCRIPTION,
                object : ItemClickEvent {
                    override fun onClickListener(view: View) {
                        var voucherDdalog = AddVoucherDialog(object :ItemTwoIntOneString{
                            override fun onClick(
                                integer: Int,
                                firstString: Int,
                                secondString: String
                            ) {
                                homeVM.addVoucher(integer,firstString,secondString)
                            }
                        })
                        voucherDdalog.show(requireActivity().supportFragmentManager,"dialog_add_voucher")
                    }
                })
        }else{HomeDashboardItem(R.drawable.ic_shopping_cart_2,
            SHOPPING_DESCRIPTION,
            object : ItemClickEvent {
                override fun onClickListener(view: View) {
                    val bottomNavigationView =
                        activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_view)
                    bottomNavigationView?.selectedItemId = R.id.fragmentShoppingScreen
                }
            })}

        val qna = HomeDashboardItem(R.drawable.ic_qna, QNA_DESCRIPTION, object : ItemClickEvent {
            override fun onClickListener(view: View) {
                view.findNavController()
                    .navigate(R.id.action_fragmentHomeScreen_to_fragmentFAQScreen)
            }
        })

        val settings =
           HomeDashboardItem(R.drawable.ic_settings,
                SETTINGS_DESCRIPTION,
                object : ItemClickEvent {
                    override fun onClickListener(view: View) {
                        val bottomNavigationView =
                            activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_view)
                        bottomNavigationView?.selectedItemId = R.id.settingsFragment
                    }
                })


        val ranking =
            HomeDashboardItem(R.drawable.ic_ranking, RANKING_DESCRIPTION, object : ItemClickEvent {
                override fun onClickListener(view: View) {
                    view.findNavController()
                        .navigate(R.id.action_fragmentHomeScreen_to_fragmentRankingScreen)
                }
            })

        val logout =
            HomeDashboardItem(R.drawable.ic_exit_icon, LOGOUT_DESCRIPTION, object : ItemClickEvent {
                override fun onClickListener(view: View) {
                    activity?.finish()
                }
            })

        val achievement = HomeDashboardItem(R.drawable.ic_achievement,
            ACHIEVEMENT_DESCRIPTION,
            object : ItemClickEvent {
                override fun onClickListener(view: View) {
                    view.findNavController()
                        .navigate(R.id.action_fragmentHomeScreen_to_fragmentAchievementScreen)
                }
            })

        val map = HomeDashboardItem(R.drawable.ic_map_2, MAP_DESCRIPTION, object : ItemClickEvent {
            override fun onClickListener(view: View) {
                val bottomNavigationView =
                    activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_view)

                bottomNavigationView?.selectedItemId = R.id.fragmentGoogleMap
            }
        })

        val barcode =
            HomeDashboardItem(R.drawable.ic_barcode, BARCODE_DESCRIPTION, object : ItemClickEvent {
                override fun onClickListener(view: View) {
                    view.findNavController()
                        .navigate(R.id.action_fragmentHomeScreen_to_fragmentTrashScreen)
                }
            })

        val company =
            HomeDashboardItem(R.drawable.ic_company, COMPANY_DESCRIPTION, object : ItemClickEvent {
                override fun onClickListener(view: View) {
                    view.findNavController()
                        .navigate(R.id.action_fragmentHomeScreen_to_fragmentCompanyScreen)
                }
            })

        val transferMoney = HomeDashboardItem(
            R.drawable.transfer_money, TRANSFER_MONEY_DESCRIPTION, object : ItemClickEvent {
                override fun onClickListener(view: View) {
                    val dialog = TransferMoneyDialog(object : ItemOneLongOneString {
                        override fun onClick(firstValue: String, id: Long) {
                            homeVM.transferMoney(id,firstValue)
                        }})
                    dialog.show(requireActivity().supportFragmentManager,"dialog_transfer_money")
                }
            })

        if (homeVM.isAdmin()) {
            return ArrayList(
                listOf(
                    qna, shopping, barcode, company, map, ranking, achievement, settings,transferMoney, logout
                )
            )
        }

        return ArrayList(
            listOf(
                qna,
                shopping,
                map,
                ranking,
                achievement,
                settings,
                transferMoney,
                logout
            )
        )
    }


}