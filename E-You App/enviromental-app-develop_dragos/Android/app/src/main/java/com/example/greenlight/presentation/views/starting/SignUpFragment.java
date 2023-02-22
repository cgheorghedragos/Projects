package com.example.greenlight.presentation.views.starting;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.greenlight.R;
import com.example.greenlight.data.requests.RequestCodes;
import com.example.greenlight.data.responses.Resource;
import com.example.greenlight.presentation.viewmodel.SignUpViewModel;
import com.example.greenlight.presentation.views.dialogs.LoadingDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SignUpFragment extends Fragment {

    @Inject
    public SignUpViewModel registerViewModel;

    private Button registerButton;
    private TextView goToLoginTextView;
    private LoadingDialog loadingDialog;
    private TextInputLayout first_name_layout;
    private TextInputLayout last_name_layout;
    private TextInputLayout username_layout;
    private TextInputLayout email_layout;
    private TextInputLayout password_layout;
    private TextInputLayout confirm_password_layout;
    private TextInputLayout birthday_layout;
    private TextInputLayout gender_layout;
    private EditText first_name_text;
    private EditText last_name_text;
    private EditText username_text;
    private EditText email_text;
    private EditText password_text;
    private EditText confirm_password_text;
    private EditText birthday_text;
    private RadioGroup genderRadioGroup;
    private Button photoLoad;

    private String imagePath;
    private List<String> imagePathList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews(view);
        setupListener(view);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    private void setupViews(@NonNull View view) {
        registerButton = requireActivity().findViewById(R.id.register_button);
        goToLoginTextView = requireActivity().findViewById(R.id.goToSignInTextview);
        first_name_layout = requireActivity().findViewById(R.id.first_name_layout_register);
        last_name_layout = requireActivity().findViewById(R.id.last_name_layout_register);
        username_layout = requireActivity().findViewById(R.id.username_layout_register);
        email_layout = requireActivity().findViewById(R.id.email_layout_register);
        password_layout = requireActivity().findViewById(R.id.password_layout_register);
        confirm_password_layout = requireActivity().findViewById(R.id.confirm_password_layout);
        genderRadioGroup = requireActivity().findViewById(R.id.genderRegisterRadioGroup);
        birthday_layout = requireActivity().findViewById(R.id.birth_date_layout);
        first_name_text = requireActivity().findViewById(R.id.first_name_text_register);
        last_name_text = requireActivity().findViewById(R.id.last_name_text_register);
        username_text = requireActivity().findViewById(R.id.username_text_register);
        email_text = requireActivity().findViewById(R.id.email_text_register);
        password_text = requireActivity().findViewById(R.id.password_text_register);
        confirm_password_text = requireActivity().findViewById(R.id.confirm_password_text);
        gender_layout = requireActivity().findViewById(R.id.genderRegisterLayout);
        birthday_text = requireActivity().findViewById(R.id.birth_date_text);
        photoLoad = requireActivity().findViewById(R.id.register_load_photo);
        loadingDialog = new LoadingDialog(view.getContext());

    }

    private void uploadPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, RequestCodes.PICK_ONE_PHOTO);
    }

    private File getFileFromUri(Uri uri){
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = requireContext().getContentResolver().query(uri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        return new File(picturePath);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodes.PICK_ONE_PHOTO && resultCode == RESULT_OK) {
            if (data != null) {
                File file = getFileFromUri(data.getData());

                RequestBody requestFile =
                        RequestBody.create(file, MediaType.parse("multipart/form-data"));

                // MultipartBody.Part is used to send also the actual file name
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

                registerViewModel.uploadPhoto(body);
            }
        }
    }

    @SuppressLint("Range")
    public void getImageFilePath(Uri uri) {

        File file = new File(uri.getPath());
        String[] filePath = file.getPath().split(":");
        String image_id = filePath[filePath.length - 1];

        Cursor cursor = requireContext().getContentResolver().query(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + " = ? ", new String[]{image_id}, null);
        if (cursor != null) {
            cursor.moveToFirst();
            imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            imagePathList.add(imagePath);
            cursor.close();
        }
    }


    private void setupListener(@NonNull View view) {
        birthday_text.setFocusable(false);
        birthday_text.setFocusableInTouchMode(false);
        first_name_text.addTextChangedListener(firstNameWatcher);
        last_name_text.addTextChangedListener(lastNameWatcher);
        username_text.addTextChangedListener(usernameWatcher);
        email_text.addTextChangedListener(emailWatcher);
        password_text.addTextChangedListener(passwordWatcher);
        confirm_password_text.addTextChangedListener(confirmPasswordWatcher);

        photoLoad.setOnClickListener(v -> {
            uploadPhoto();
        });

        birthday_text.setOnClickListener(v -> {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(requireActivity().getSupportFragmentManager(), "datePicker");
        });

        registerButton.setOnClickListener(v -> {

            if (!verifyLengthOfTextBoxes() && verifyMatchPassword() ) {
                if(verifyIsEmail()){
                    registerViewModel.onClick();
                }
            }
        });

        goToLoginTextView.setOnClickListener(e -> {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment);
        });

        registerViewModel.getRegisterResponse().observe(requireActivity(), user -> {
            switch (user.getType()) {
                case INITIAL:
                    break;
                case LOADING:
                    loadingDialog.showDialog();
                    break;
                case SUCCESS:
                    loadingDialog.hideDialog();
                    Toast.makeText(getContext(), "Registered successfully", Toast.LENGTH_LONG).show();
                    Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment);
                    registerViewModel.responseLiveData.setValue(Resource.initial());
                    registerViewModel.sendPhotoResponse.setValue(Resource.initial());
                    break;
                case ERROR:
                    loadingDialog.hideDialog();
                    Log.e("ERR",user.getError().toString());
                    Toast.makeText(getContext(), "Unable to reach connection, " +
                            "please ensure the access to the internet or try again later.", Toast.LENGTH_LONG).show();
                    break;
            }
        });

        registerViewModel.getUploadPhotoResponse().observe(requireActivity(), loadPhoto -> {
            switch (loadPhoto.getType()) {
                case INITIAL:
                    break;
                case LOADING:
                    loadingDialog.showDialog();
                    break;
                case SUCCESS:
                    loadingDialog.hideDialog();
                    if (loadPhoto.getData() != null) {
                        Toast.makeText(getContext(), "Photo loaded successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Failed to load Photo", Toast.LENGTH_LONG).show();
                    }
                    break;
                case ERROR:
                    Log.e("ERR",loadPhoto.getError().toString());
                    loadingDialog.hideDialog();
                    Toast.makeText(getContext(), "Unable to reach connection, " +
                            "please ensure the access to the internet or try again later.", Toast.LENGTH_LONG).show();
                    break;
            }
        });


        registerViewModel.birthday.observe(requireActivity(), textChanged -> {
            birthday_text.setText(textChanged);
            clearError(birthday_layout);
        });

        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radBtn = requireActivity().findViewById(group.getCheckedRadioButtonId());
                registerViewModel.updateGender(radBtn.getText().toString());
                clearError(gender_layout);
            }
        });
    }


    private boolean verifyMatchPassword() {
        if (comparePassword(password_text.getText().toString(), confirm_password_text.getText().toString())) {
            return true;
        } else {
            String error = "Password doesn't match!";
            setErrorToLayout(password_layout, error);
            setErrorToLayout(confirm_password_layout, error);
            return false;
        }
    }

    private boolean verifyIsEmail(){
        String email = email_text.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(email.matches(emailPattern)){
            return true;
        } else{
            String error = "Email is invalid";
            setErrorToLayout(email_layout,error);
            return false;
        }
    }

    private boolean verifyLengthOfTextBoxes() {
        String message = "This should have at least 4 characters.";
        String emptyMsg = "This field is empty.";
        boolean foundOneFieldError = false;

        if (check4Characters(first_name_text)) {
            setErrorToLayout(first_name_layout, message);
            foundOneFieldError = true;
        }

        if (check4Characters(last_name_text)) {
            setErrorToLayout(last_name_layout, message);
            foundOneFieldError = true;
        }

        if (check4Characters(username_text)) {
            setErrorToLayout(username_layout, message);
            foundOneFieldError = true;
        }

        if (check4Characters(email_text)) {
            setErrorToLayout(email_layout, message);
            foundOneFieldError = true;
        }

        if (check4Characters(password_text)) {
            setErrorToLayout(password_layout, message);
            foundOneFieldError = true;
        }

        if (check4Characters(confirm_password_text)) {
            setErrorToLayout(confirm_password_layout, message);
            foundOneFieldError = true;
        }

        if (isEmpty(birthday_text)) {
            setErrorToLayout(birthday_layout, emptyMsg);
            foundOneFieldError = true;
        }

        if (genderRadioGroup.getCheckedRadioButtonId() == -1) {
            setErrorToLayout(gender_layout, emptyMsg);
            foundOneFieldError = true;
        }

        return foundOneFieldError;
    }

    public Boolean comparePassword(String pass, String confirmPass) {
        return pass.equals(confirmPass);
    }

    public Boolean check4Characters(EditText e) {
        return e.getText().toString().trim().length() < 4;
    }

    public Boolean isEmpty(EditText e){
        return e.getText().toString().trim().length() == 0;
    }


    public void setErrorToLayout(TextInputLayout layout, String error) {
        layout.setError(error);
        layout.requestFocus();
    }

    public void clearError(TextInputLayout layout) {
        layout.setErrorEnabled(false);
    }

    private final TextWatcher firstNameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            registerViewModel.updateFirstName(s.toString());
            clearError(first_name_layout);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final TextWatcher lastNameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            registerViewModel.updateLastName(s.toString());
            clearError(last_name_layout);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final TextWatcher usernameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            registerViewModel.updateUserName(s.toString());
            clearError(username_layout);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            registerViewModel.updateEmail(s.toString());
            clearError(email_layout);
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
            registerViewModel.updatePassword(s.toString());
            clearError(password_layout);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final TextWatcher confirmPasswordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            clearError(confirm_password_layout);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}