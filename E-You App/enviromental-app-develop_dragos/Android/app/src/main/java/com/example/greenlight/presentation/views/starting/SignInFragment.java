package com.example.greenlight.presentation.views.starting;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greenlight.R;
import com.example.greenlight.data.SessionManager;
import com.example.greenlight.data.responses.Resource;
import com.example.greenlight.data.responses.UserLoginResponse;
import com.example.greenlight.presentation.viewmodel.LoginViewModel;
import com.example.greenlight.presentation.views.application.ApplicationActivity;
import com.example.greenlight.presentation.views.dialogs.LoadingDialog;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class SignInFragment extends Fragment {
    @Inject
    public LoginViewModel loginViewModel;

    private Button loginButton;
    private TextView goToRegisterTextView;
    private TextInputLayout username_layout;
    private TextInputLayout password_layout;
    private EditText usernameText;
    private EditText passwordText;
    private LoadingDialog loadingDialog;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unsetBackButton();
    }

    private void unsetBackButton() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().getWindow().setNavigationBarColor(getResources().getColor(R.color.light_black_green));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews();
        setupListener(view);

    }

    private void setupViews() {
        loginButton = requireActivity().findViewById(R.id.loginButton);
        goToRegisterTextView = requireActivity().findViewById(R.id.goToSignUpTextview);
        passwordText = requireActivity().findViewById(R.id.password_login_text);
        usernameText = requireActivity().findViewById(R.id.username_login_text);
        username_layout = requireActivity().findViewById(R.id.username_layout_login);
        password_layout = requireActivity().findViewById(R.id.passwordLayoutLogin);
        loadingDialog = new LoadingDialog(getContext());

    }

    private void setupListener(@NonNull View view) {
        passwordText.addTextChangedListener(passwordWatcher);
        usernameText.addTextChangedListener(usernameWatcher);

        loginButton.setOnClickListener(e -> {
            if(!verifyLengthOfTextBoxes()){
            loginViewModel.onClick();
            }
        });

        goToRegisterTextView.setOnClickListener(e -> {
            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_signUpFragment);
        });


        loginViewModel.getLoginResponse().observe(requireActivity(), userLoginResponseResource -> {

            switch (userLoginResponseResource.getType()) {
                case INITIAL:
                    break;
                case LOADING:
                    loadingDialog.showDialog();
                    break;
                case SUCCESS:
                    loadingDialog.hideDialog();

                    if(userLoginResponseResource.getData() != null){
                        String token = "Bearer "+userLoginResponseResource.getData().getJwt();
                        loginViewModel.saveAuthToken(token);
                        loginViewModel.saveUser(userLoginResponseResource.getData().getUser());
                       startActivity(new Intent(requireActivity(),ApplicationActivity.class));
                        requireActivity().finish();
                    } else {
                        Toast.makeText(getContext(),userLoginResponseResource.getData().getError(),Toast.LENGTH_LONG).show();
                    }
                    break;
                case ERROR:
                   loadingDialog.hideDialog();
                    Log.e("LOGIN",userLoginResponseResource.getError().toString());
                    Toast.makeText(getContext(),"Unable to reach connection, " +
                            "please ensure the access to the internet or try again later.",Toast.LENGTH_LONG).show();
                    break;
            }
        });
    }

    public Boolean isEmpty(EditText e) {
        return e.getText().toString().trim().length() < 4;
    }

    private boolean verifyLengthOfTextBoxes() {
        String message = "This field should have at least 4 characters.";
        boolean foundOneFieldError = false;

        if (isEmpty(usernameText)) {
            setErrorToLayout(username_layout, message);
            foundOneFieldError = true;
        }

        if (isEmpty(passwordText)) {
            setErrorToLayout(password_layout, message);
            foundOneFieldError = true;
        }

        return foundOneFieldError;
    }

    public void setErrorToLayout(TextInputLayout layout, String error) {
        layout.setError(error);
        layout.requestFocus();
    }
        // Text Watchers

    private final TextWatcher usernameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            loginViewModel.updateUserName(s.toString().trim());
            username_layout.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            loginViewModel.updatePassword(s.toString().trim());
            password_layout.setErrorEnabled(false);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}