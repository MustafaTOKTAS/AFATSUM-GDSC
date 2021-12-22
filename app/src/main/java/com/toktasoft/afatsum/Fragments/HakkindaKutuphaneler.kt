package com.toktasoft.afatsum.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toktasoft.afatsum.databinding.FragmentHakkindaKutuphanelerBinding

class HakkindaKutuphanelerFragment : Fragment() {

    private var _binding: FragmentHakkindaKutuphanelerBinding? = null
    private val b get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        _binding = FragmentHakkindaKutuphanelerBinding.inflate(inflater, container, false)
        val view = b.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b.tv10.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri.parse
                    ("https://github.com/firebase/firebase-android-sdk"))
            )
        }

        b.tv15.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse
                    ("https://github.com/daimajia/AndroidViewAnimations")))
        }
    }

}