package com.toktasoft.afatsum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.toktasoft.afatsum.databinding.FragmentDialogEpostaGonderBinding

class DialogEpostaGonderFragment : DialogFragment() {

    private var _binding: FragmentDialogEpostaGonderBinding? = null
    private val b get() = _binding!!
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentDialogEpostaGonderBinding.inflate(inflater, container, false)
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

        b.btnDialogGonder.setOnClickListener {

            if (!b.etDialogEposta.text.isNullOrEmpty() && !b.etDialogParola.text.isNullOrEmpty()){

                if (b.etDialogEposta.text.toString().length >= 15 && b.etDialogParola.text.toString().length >= 6){

                    auth.signInWithEmailAndPassword(b.etDialogEposta.text.toString(),b.etDialogParola.text.toString()).addOnSuccessListener {

                        if (auth.currentUser != null) {
                            if (auth.currentUser!!.isEmailVerified == false) {

                                auth.currentUser!!.sendEmailVerification().addOnSuccessListener {

                                    Toast.makeText(activity, "Onay mail'i gönderildi", Toast.LENGTH_SHORT).show()
                                    dialog!!.dismiss()
                                }
                                    .addOnFailureListener {
                                        Toast.makeText(activity, "HATA ! Onay mail'i gönderilemedi", Toast.LENGTH_SHORT).show()
                                    }
                            } else {
                                Toast.makeText(activity, "e-posta adresiniz zaten onaylı", Toast.LENGTH_SHORT).show()
                                dialog!!.dismiss()
                            }

                        }
                        auth.signOut()

                    }
                        .addOnFailureListener {
                            Toast.makeText(activity,it.localizedMessage, Toast.LENGTH_SHORT).show()
                        }

                } else { Snackbar.make(it,"Bilgileri eksiksiz ve doğru giriniz", Snackbar.LENGTH_SHORT).show()

                }
            }else {
                Snackbar.make(it,"Bilgileri eksiksiz ve doğru giriniz", Snackbar.LENGTH_SHORT).show()

            }

        }


    }

}