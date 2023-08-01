package com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ciurezu.gheorghe.dragos.greenlight.R
import java.lang.StringBuilder

class FragmentTab constructor(val infoText: String, val imageID: Int) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoText = view.findViewById<TextView>(R.id.info_text)
        val image = view.findViewById<ImageView>(R.id.tab_fragment_imageView)

        infoText.text = this@FragmentTab.infoText
        image.setImageResource(imageID)
    }

}