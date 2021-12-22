package com.toktasoft.afatsum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.toktasoft.afatsum.Fragments.ParolaUnuttumFragment
import com.toktasoft.afatsum.databinding.ActivityGirisYapBinding

class GirisYapActivity : AppCompatActivity() {

    private lateinit var b: ActivityGirisYapBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {

        b= ActivityGirisYapBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        auth = Firebase.auth
        db = Firebase.firestore

        aktifKullaniciVarsaVeMailiOnayliysaGirisYap()
    }

    fun tvParolamiUnuttumTiklandi (view : View){

        if (auth.currentUser != null) auth.signOut()
        val dialogGoster = ParolaUnuttumFragment()
        dialogGoster.show(supportFragmentManager,"ParolamiUnuttum")
        dialogGoster.isCancelable = true
    }

    fun tvDogrulamaEpostasiTekrarGonderTiklandi (view : View){
        if (auth.currentUser != null) auth.signOut()
        val dialogGoster = DialogEpostaGonderFragment()
        dialogGoster.show(supportFragmentManager,"DialogEpostaGonder")
        dialogGoster.isCancelable = true
    }

    fun tvUyeOlTiklandi (view: View){

        if(auth.currentUser != null) auth.signOut()
        val intent = Intent(this,UyeOlActivity::class.java)
        startActivity(intent)
    }

    fun btnGirisYapTiklandi (view : View){
        if (!b.etEposta.text.isNullOrEmpty() && !b.etParola.text.isNullOrEmpty()){

            val yuklemeAlertDialog =View.inflate(this, R.layout.yukleme_alert_dialog, null)
            val builder1 = AlertDialog.Builder(this)
            builder1.setView(yuklemeAlertDialog)
            val dialog = builder1.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(false)


            auth.signInWithEmailAndPassword(b.etEposta.text.toString(),b.etParola.text.toString())
                .addOnSuccessListener {

                    if (auth.currentUser != null) {

                        if (auth.currentUser!!.isEmailVerified){

                            val intent = Intent(this,AnasayfaActivity::class.java)
                            startActivity(intent)
                            finish()
                            dialog.dismiss()

                        } else {
                            Snackbar.make(view,"Lütfen e-posta adresinize gelen mail'i onaylayınız !",
                                Snackbar.LENGTH_LONG).show()
                            auth.signOut()
                            dialog.dismiss()
                        }
                    }

                }.addOnFailureListener {
                    dialog.dismiss()
                    Snackbar.make(view,it.localizedMessage, Snackbar.LENGTH_LONG).show()

                }
        }
        else Snackbar.make(view,"Alanlar boş bırakılamaz !", Snackbar.LENGTH_LONG).show()
    }

    fun aktifKullaniciVarsaVeMailiOnayliysaGirisYap (){
        if(auth.currentUser != null){

            if (auth.currentUser!!.isEmailVerified){
                val intent = Intent(this, AnasayfaActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    fun kosullarTiklandi (view : View){
        val intent = Intent(this,UygulamaHakkindaActivity::class.java)
        startActivity(intent)
    }



}