package com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.GreenLightActivity
import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.viewmodel.SignInViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FragmentSignIn : Fragment() {
    @Inject
    lateinit var signInVM: SignInViewModel

    private lateinit var loginButton: Button
    private lateinit var goToSignUpTextViewButton: TextView
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners()
    }

    private fun setupViews(view: View) {
        loginButton = view.findViewById(R.id.loginButton)
        goToSignUpTextViewButton = view.findViewById(R.id.signInToSignUpTextViewButton)
        usernameLayout = view.findViewById(R.id.usernameSingInTextLayout)
        passwordLayout = view.findViewById(R.id.passwordSignInTextLayout)
        usernameText = view.findViewById(R.id.usernameSignInText)
        passwordText = view.findViewById(R.id.passwordSignInText)
    }

    private fun setupListeners() {
        loginButton.setOnClickListener {
//            startActivity(Intent(context,GreenLightActivity::class.java))
            signInVM.signInUser(usernameText.text.toString(), passwordText.text.toString())
        }

        goToSignUpTextViewButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSignIn_to_fragmentSignUpName)
        }

        setupLoginObserver()
    }

    private fun setupLoginObserver() {
        signInVM.signInResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    makeText(
                        context,
                        "Loading",
                        android.widget.Toast.LENGTH_LONG
                    ).show()
                }
                is Resource.Success -> {
                    signInVM.saveAccessToken(it.data.accessToken)

                    startActivity(Intent(context,GreenLightActivity::class.java))
//                    findNavController().navigate(R.id.action_fragmentSignIn_to_fragmentRankingScreen)
                }
                is Resource.Error -> {
                    makeText(
                        context,
                        it.errorMessage,
                        android.widget.Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }
}