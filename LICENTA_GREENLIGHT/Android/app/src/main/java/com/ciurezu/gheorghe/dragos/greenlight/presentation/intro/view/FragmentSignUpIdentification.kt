package com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view

import android.app.Activity
import android.content.Intent
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
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.view.CheckStorage
import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.viewmodel.SignUpViewModel
import com.ciurezu.gheorghe.dragos.greenlight.util.FileUploadUtil
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.AndroidSupportInjection
import java.io.File
import java.lang.NumberFormatException
import javax.inject.Inject


class FragmentSignUpIdentification : Fragment() {
    @Inject
    lateinit var signUpVM: SignUpViewModel

    private lateinit var townLayout: TextInputLayout
    private lateinit var streetLayout: TextInputLayout
    private lateinit var numberLayout: TextInputLayout

    private lateinit var townText: EditText
    private lateinit var streetText: EditText
    private lateinit var numberText: EditText

    private lateinit var addPhoto: Button
    private lateinit var nextBtn: Button
    private lateinit var backBtn: Button

    private var photoImage: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_identification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListener()
        setupObserver()
    }

    private fun setupObserver() {
        signUpVM.errorResponse.observe(viewLifecycleOwner){
            if(it.length > 2){
                Toast.makeText(context,it.toString(),Toast.LENGTH_LONG).show()
                signUpVM.errorResponse.postValue("")
            }
        }

        signUpVM.updateResponse.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(R.id.action_fragmentSignUpIdentification_to_fragmentSignIn)
                signUpVM.updateResponse.postValue(false)
            }
        }
    }

    private fun setupListener() {
        addPhoto.setOnClickListener {

            if (CheckStorage.isReadExternalStoragePermissionGranted(it.context)) {
                pickImage()
            } else {
                requestReadExternalStoragePermission()
            }
        }

        nextBtn.setOnClickListener {
            if (validateInputs()) {
                signUpVM.updateUser(
                    photo = photoImage,
                    town = townText.text.toString(),
                    street = streetText.text.toString(),
                    streetNr = numberText.text.toString().toInt()
                )

            }
        }

        backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSignUpIdentification_to_fragmentChooseSignInOrUp)
        }
    }


    private fun setupViews(view: View) {
        townLayout = view.findViewById(R.id.townLayout)
        streetLayout = view.findViewById(R.id.streetLayout)
        numberLayout = view.findViewById(R.id.numberLayout)

        townText = view.findViewById(R.id.townText)
        streetText = view.findViewById(R.id.streetText)
        numberText = view.findViewById(R.id.numberText)

        addPhoto = view.findViewById(R.id.add_profile_photo)

        backBtn = view.findViewById(R.id.cancelBtnUpdateUser)
        nextBtn = view.findViewById(R.id.goToLogin)
    }


    private fun requestReadExternalStoragePermission() {
        CheckStorage.requestReadExternalStoragePermission(requireActivity())
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(intent, 3)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 3 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val fileList = FileUploadUtil.getFileListFromUri(data, requireContext())

                photoImage = fileList[0]
            }

        }
    }

    private fun validateInputs(): Boolean {
        var notFound = true
        try {
            val number = numberText.text.toString().toInt()
            if(number <= 0 ){
                numberLayout.error = "The number should be greater than 0"
                notFound = false
            }else{
                numberLayout.error =""
            }
        }catch ( err : NumberFormatException){
            numberLayout.error = "Please set a valid number"
            notFound = false
        }
       if(streetText.length() < 3){
           streetLayout.error = "Street should exists!"
           notFound = false
       }else{
           streetLayout.error = ""
       }

        if(townText.text.toString().length <3){
            townLayout.error = "Town should exists!"
            notFound = false
        }else{
            townLayout.error =""
        }

        return notFound
    }
}


