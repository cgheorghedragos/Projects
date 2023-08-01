package com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ciurezu.gheorghe.dragos.greenlight.R
import dagger.android.support.AndroidSupportInjection

class FragmentChooseSignInOrUp : Fragment() {

    private lateinit var createAccountButton: Button
    private lateinit var signInButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_sign_in_or_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners()
    }

    private fun setupViews(view: View) {
        createAccountButton = view.findViewById(R.id.chooseSignUp)
        signInButton = view.findViewById(R.id.chooseSignIn)
    }

    private fun setupListeners() {
        createAccountButton.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_fragmentChooseSignInOrUp_to_fragmentSignUpName)
        }

        signInButton.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_fragmentChooseSignInOrUp_to_fragmentSignIn)
        }
    }
}