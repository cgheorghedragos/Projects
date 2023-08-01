package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.ranking.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_users_by_coins.response.GetAllUsersPctResponse
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.ranking.adapter.RankingInfoAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.ranking.viewmodel.RankingViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FragmentRankingScreen : Fragment() {

    @Inject
    lateinit var rankingVM: RankingViewModel

    private lateinit var firstWinnerName: TextView
    private lateinit var secondWinnerName: TextView
    private lateinit var thirdWinnerName: TextView
    private lateinit var firstWinnerCoins: TextView
    private lateinit var secondWinnerCoins: TextView
    private lateinit var thirdWinnerCoins: TextView
    private lateinit var firstWinnerImage: ImageView
    private lateinit var secondWinnerImage: ImageView
    private lateinit var thirdWinnerImage: ImageView
    private lateinit var rankingRVAdapter: RankingInfoAdapter
    private lateinit var rankingRV: RecyclerView
    private lateinit var closeBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners()
    }

    private fun setupViews(view: View) {
        firstWinnerName = view.findViewById(R.id.usernameWinner)
        secondWinnerName = view.findViewById(R.id.username2Winner)
        thirdWinnerName = view.findViewById(R.id.username3Winner)

        firstWinnerCoins = view.findViewById(R.id.coinsWinner)
        secondWinnerCoins = view.findViewById(R.id.coins2Winner)
        thirdWinnerCoins = view.findViewById(R.id.coins3Winner)

        firstWinnerImage = view.findViewById(R.id.firstWinnerImage)
        secondWinnerImage = view.findViewById(R.id.secondWinnerImage)
        thirdWinnerImage = view.findViewById(R.id.thirdWinnerImage)

        rankingRV = view.findViewById(R.id.rankingRecycleView)
        rankingRV.layoutManager = LinearLayoutManager(view.context)

        rankingRVAdapter = RankingInfoAdapter()

        rankingRV.adapter = rankingRVAdapter

        closeBtn = view.findViewById(R.id.close_button_ranking)
    }

    private fun setupListeners() {
        setupTopChangedObserver()
        closeBtn.setOnClickListener { findNavController().navigate(R.id.action_fragmentRankingScreen_to_fragmentHomeScreen) }
    }

    private fun setupTopChangedObserver() {
        rankingVM.fetchUsersResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    val response = (it.data.payload as ArrayList);
                    updateTop3UsersData(response)

                    rankingRVAdapter.updateListAdapter(response.subList(3, response.size))
                }

                is Resource.Error -> {
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateTop3UsersData(users: List<GetAllUsersPctResponse>) {
        firstWinnerName.text = users[0].username
        secondWinnerName.text = users[1].username
        thirdWinnerName.text = users[2].username

        firstWinnerCoins.text = String.format(users[0].score.toString())
        secondWinnerCoins.text = String.format(users[1].score.toString())
        thirdWinnerCoins.text = String.format(users[2].score.toString())

        Glide.with(firstWinnerImage.context)
            .load(users[0].photoUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(firstWinnerImage)

        Glide.with(secondWinnerImage.context)
            .load(users[1].photoUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(secondWinnerImage)

        Glide.with(thirdWinnerImage.context)
            .load(users[2].photoUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(thirdWinnerImage)
    }
}