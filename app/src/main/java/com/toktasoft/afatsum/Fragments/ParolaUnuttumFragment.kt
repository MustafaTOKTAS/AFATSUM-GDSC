package com.toktasoft.afatsum.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.toktasoft.afatsum.databinding.FragmentParolaUnuttumBinding


class ParolaUnuttumFragment : DialogFragment() {

    private var _binding: FragmentParolaUnuttumBinding? = null
    private val b get() = _binding!!
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentParolaUnuttumBinding.inflate(inflater, container, false)
        val view = b.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        b.btnDialogGonder2.setOnClickListener {
            auth.sendPasswordResetEmail(b.etDialogEposta.text.toString())
                .addOnSuccessListener {

                    Toast.makeText(activity, "Parola Sıfırlama Mail'i Gönderildi", Toast.LENGTH_SHORT).show()
                    dialog!!.dismiss()
            }
                .addOnFailureListener {
                    Toast.makeText(activity, it.localizedMessage, Toast.LENGTH_LONG).show()

                }
        }


    }




}