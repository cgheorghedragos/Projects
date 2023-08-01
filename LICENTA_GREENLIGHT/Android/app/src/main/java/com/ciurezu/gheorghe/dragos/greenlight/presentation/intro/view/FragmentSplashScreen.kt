package com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.ciurezu.gheorghe.dragos.greenlight.R
import dagger.android.support.AndroidSupportInjection


class FragmentSplashScreen : Fragment() {
    companion object {
        const val TIME_MILLIS_TO_CHANGE_SPLASH_SCREEN = 4500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed(
            { findNavController(view).navigate(R.id.action_fragmentSplashScreen_to_fragmentIntro) },
            TIME_MILLIS_TO_CHANGE_SPLASH_SCREEN
        )
    }


}