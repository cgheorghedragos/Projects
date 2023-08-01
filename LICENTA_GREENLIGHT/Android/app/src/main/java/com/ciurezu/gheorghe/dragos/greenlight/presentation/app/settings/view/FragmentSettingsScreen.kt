package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.settings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.interfaces.ItemClickString
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.settings.dialog.UpdateDialogWithOneString
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.settings.viewmodel.ProfileViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FragmentSettingsScreen : Fragment() {
    @Inject
    lateinit var profileVm: ProfileViewModel

    private lateinit var townInfo: ConstraintLayout
    private lateinit var streetInfo: ConstraintLayout
    private lateinit var streetNrInfo: ConstraintLayout
    private lateinit var emailInfo: ConstraintLayout
    private lateinit var passwordNameInfo: ConstraintLayout
    private lateinit var phoneNumberInfo: ConstraintLayout

    private lateinit var townText: TextView
    private lateinit var streetText: TextView
    private lateinit var streetNrText: TextView
    private lateinit var passwordText: TextView
    private lateinit var emailText: TextView
    private lateinit var coinsText: TextView
    private lateinit var scoreText: TextView
    private lateinit var phoneNumber: TextView

    private lateinit var userPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupListeners(view)
        initObservers()

        profileVm.getUser()
    }

    private fun setupListeners(view: View) {
        setupEmailListener()
        setupPhoneNumberListener()
        setupStreetListener()
        setupStreetNrListener()
        setupTownListener()
    }

    private fun setupTownListener() {
        townInfo.findViewById<CardView>(R.id.update_sss).setOnClickListener {
            UpdateDialogWithOneString(
                "Enter the new town",
                townText.text.toString(),
                object : ItemClickString {
                    override fun onClick(string: String) {
                        profileVm.updateTown(string)
                    }
                }).show(requireActivity().supportFragmentManager, "update_town")
        }
    }

    private fun setupStreetNrListener() {
        streetNrInfo.findViewById<CardView>(R.id.update_sss).setOnClickListener {
            UpdateDialogWithOneString(
                "Enter the new street nr",
                streetNrText.text.toString(),
                object : ItemClickString {
                    override fun onClick(string: String) {
                        profileVm.updateStreetNr(string)
                    }
                }).show(requireActivity().supportFragmentManager, "update_street_nr")
        }
    }

    private fun setupStreetListener() {
        streetInfo.findViewById<CardView>(R.id.update_sss).setOnClickListener {
            UpdateDialogWithOneString(
                "Enter the new street",
                streetText.text.toString(),
                object : ItemClickString {
                    override fun onClick(string: String) {
                        profileVm.updateStreet(string)
                    }
                }).show(requireActivity().supportFragmentManager, "update_street")
        }
    }

    private fun setupPhoneNumberListener() {
        phoneNumberInfo.findViewById<CardView>(R.id.update_sss).setOnClickListener {
            UpdateDialogWithOneString(
                "Enter the new phone number",
                phoneNumber.text.toString(),
                object : ItemClickString {
                    override fun onClick(string: String) {
                        profileVm.updatePhoneNumber(string)
                    }
                }).show(requireActivity().supportFragmentManager, "update_phone_number")
        }
    }

    private fun setupEmailListener() {
        emailInfo.findViewById<CardView>(R.id.update_sss).setOnClickListener {
            UpdateDialogWithOneString(
                "Enter the new Email",
                emailText.text.toString(),
                object : ItemClickString {
                    override fun onClick(string: String) {
                        profileVm.updateEmail(string)
                    }
                }).show(requireActivity().supportFragmentManager, "update_email")
        }
    }

    private fun initObservers() {
        initUserObserver()
        initErrorMessageObserver()
    }

    private fun initErrorMessageObserver() {
        profileVm.errorMessageResponse.observe(viewLifecycleOwner) {
            if (it.length > 2) {
                Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
                profileVm.errorMessageResponse.postValue("")
            }
        }
    }

    private fun initUserObserver() {
        profileVm.userResponse.observe(viewLifecycleOwner) {
            emailText.text = it.email
            coinsText.text = it.coins.toString()
            scoreText.text = it.score.toString()
            phoneNumber.text = it.phoneNumber
            Glide.with(userPhoto.context)
                .load(it.photUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(userPhoto)

            //address
            it.address?.let { it2->
                townText.text = it.address.town
                streetText.text = it.address.street
                streetNrText.text = it.address.streetNr.toString()
            }

        }
    }

    private fun initViews(view: View) {
        initLayouts(view)
        initEmail(view)
        initStreet(view)
        initStreetNr(view)
        initTown(view)
        initPassword(view)
        initPhoneNumber(view)
        initUserPhotoView(view)

        scoreText = view.findViewById(R.id.score_info)
        coinsText = view.findViewById(R.id.coins_info)
    }

    private fun initUserPhotoView(view: View) {
        userPhoto = view.findViewById(R.id.profile_photo_view)
    }

    private fun initPassword(view: View) {
        passwordText = passwordNameInfo.findViewById(R.id.item_profile_data_text)
        passwordNameInfo.findViewById<ImageView>(R.id.item_profile_data_image)
            .setImageResource(R.drawable.ic_password)
        passwordText.text = StringBuilder("Password").toString()
    }

    private fun initEmail(view: View) {
        emailText = emailInfo.findViewById(R.id.item_profile_data_text)
        emailInfo.findViewById<ImageView>(R.id.item_profile_data_image)
            .setImageResource(R.drawable.ic_email)
    }

    private fun initStreet(view: View) {
        streetText = streetInfo.findViewById(R.id.item_profile_data_text)
        streetInfo.findViewById<ImageView>(R.id.item_profile_data_image)
            .setImageResource(R.drawable.ic_street)
    }

    private fun initStreetNr(view: View) {
        streetNrText = streetNrInfo.findViewById(R.id.item_profile_data_text)
        streetNrInfo.findViewById<ImageView>(R.id.item_profile_data_image)
            .setImageResource(R.drawable.ic_number)
    }

    private fun initTown(view: View) {
        townText = townInfo.findViewById(R.id.item_profile_data_text)
        townInfo.findViewById<ImageView>(R.id.item_profile_data_image)
            .setImageResource(R.drawable.ic_town)
    }

    private fun initPhoneNumber(view: View) {
        phoneNumber = phoneNumberInfo.findViewById(R.id.item_profile_data_text)
        phoneNumberInfo.findViewById<ImageView>(R.id.item_profile_data_image)
            .setImageResource(R.drawable.ic_phone)
    }

    private fun initLayouts(view: View) {
        townInfo = view.findViewById<ConstraintLayout>(R.id.town_info)
        streetInfo = view.findViewById<ConstraintLayout>(R.id.street_info)
        streetNrInfo = view.findViewById<ConstraintLayout>(R.id.street_nr_info)
        passwordNameInfo = view.findViewById<ConstraintLayout>(R.id.password_info)
        emailInfo = view.findViewById<ConstraintLayout>(R.id.email_name_info)
        phoneNumberInfo = view.findViewById(R.id.phone_number_info)
    }
}