package com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user.request.UserSignUpRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.check_username.check_email.UserWithEmail
import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.viewmodel.SignUpViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class FragmentSignUpName : Fragment() {
    @Inject
    lateinit var signUpVM: SignUpViewModel

    lateinit var usernameTextLayout: TextInputLayout
    lateinit var emailTextLayout: TextInputLayout
    lateinit var phoneNumberTextLayout: TextInputLayout
    lateinit var passwordTextLayout: TextInputLayout
    lateinit var confirmPasswordTextLayout: TextInputLayout

    lateinit var usernameText: EditText
    lateinit var emailText: EditText
    lateinit var phoneNumberText: EditText
    lateinit var passwordText: EditText
    lateinit var confirmPasswordText: EditText

    lateinit var nextButton: Button
    lateinit var backBtn: Button

    companion object {
        const val DEFAULT_REGISTER_ERROR_MESSAGE = "Register failed. Please try again!"
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
        return inflater.inflate(R.layout.fragment_sign_up_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupListeners()
    }

    private fun initViews(view: View) {

        usernameTextLayout = view.findViewById(R.id.usernameSignUpLayout)
        emailTextLayout = view.findViewById(R.id.emailSignUpLayout)
        phoneNumberTextLayout = view.findViewById(R.id.phoneNumberSignUpLayout)
        passwordTextLayout = view.findViewById(R.id.passwordSignUpLayout)
        confirmPasswordTextLayout = view.findViewById(R.id.confirmPasswordSignUpLayout)

        usernameText = view.findViewById(R.id.usernameSignUpText)
        emailText = view.findViewById(R.id.emailSignUpText)
        phoneNumberText = view.findViewById(R.id.phoneNumberSignUpText)
        passwordText = view.findViewById(R.id.passwordSignUpText)
        confirmPasswordText = view.findViewById(R.id.confirmPasswordSignUpText)

        nextButton = view.findViewById(R.id.goToAddressFieldButton)
        backBtn = view.findViewById(R.id.cancelRegisterName)
    }

    private fun setupListeners() {
        nextButtonListener()
        backButtonListener()

        setupCheckUserAvailabilityListenerObserver()
        setupErrorObserver()
        signUpObserver()
    }

    private fun setupErrorObserver() {
        signUpVM.errorResponse.observe(viewLifecycleOwner) {
            if (it.length > 2) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                signUpVM.errorResponse.postValue("")
            }
        }
    }

    private fun backButtonListener() {
        backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSignUpName_to_fragmentChooseSignInOrUp)
        }
    }

    private fun nextButtonListener() {
        nextButton.setOnClickListener {
            if (validateInputs()) {
                val usrEm = UserWithEmail(
                    username = usernameText.text.toString(),
                    email = emailText.text.toString()
                )

                signUpVM.checkUserEmail(userEm = usrEm)
            }
        }
    }

    private fun validateInputs(): Boolean {
        var notFound = true

        if (usernameText.text.toString().length < 4) {
            usernameTextLayout.error = "Username should has atleast 4 characters"
            notFound = false
        } else {
            usernameTextLayout.error = null
        }
        if (!passwordText.text.toString().equals(confirmPasswordText.text.toString())) {
            passwordTextLayout.error = "Password Doesn't match"
            notFound = false
        } else {
            passwordTextLayout.error = null
        }

        if (phoneNumberText.text.toString().length != 10) {
            phoneNumberTextLayout.error = "Phone number should have 10 characters"
            notFound = false
        } else {
            phoneNumberTextLayout.error = null
        }

        if (passwordText.text.toString().length < 6) {
            confirmPasswordTextLayout.error = "Password should have atleast 6 characters"
            notFound = false
        } else {
            confirmPasswordTextLayout.error = null
        }
        return notFound;
    }

    private fun setupCheckUserAvailabilityListenerObserver() {
        signUpVM.checkUserResponse.observe(viewLifecycleOwner) {
            if (it) {
                val addUser: UserSignUpRequest = UserSignUpRequest(
                    username = usernameText.text.toString(),
                    coins = 0,
                    score = 0,
                    password = passwordText.text.toString(),
                    email = emailText.text.toString(),
                    phoneNumber = phoneNumberText.text.toString()
                )
                signUpVM.signUpUser(addUser)

            }
        }
    }

    private fun signUpObserver() {
        signUpVM.singUpResponse.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_fragmentSignUpName_to_fragmentSignUpIdentification)
                signUpVM.singUpResponse.postValue(false)
            }
        }
    }

}