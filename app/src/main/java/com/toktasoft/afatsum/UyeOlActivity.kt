package com.toktasoft.afatsum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.toktasoft.afatsum.databinding.ActivityUyeOlBinding

class UyeOlActivity : AppCompatActivity() {

    private lateinit var b: ActivityUyeOlBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {

        b = ActivityUyeOlBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        auth = Firebase.auth
        db = Firebase.firestore

    }

    fun tvGizlilikVeKosullarTiklandi (view: View){
        val intent = Intent(this,UygulamaHakkindaActivity::class.java)
        startActivity(intent)

    }

    fun onayMailiGonder(){
        if(auth.currentUser != null){

            auth.currentUser!!.sendEmailVerification().addOnSuccessListener {
                Toast.makeText(applicationContext,"Onay maili Gönderildi", Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener {
                    Toast.makeText(applicationContext,it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
        }

    }

    fun parolaKontrol () : Boolean  {

        if (!b.etParola.text.isNullOrEmpty() && !b.etParolaTekrar.text.isNullOrEmpty()){
            if (b.etParola.text!!.length >= 6){
                if (b.etParolaTekrar!!.text.toString() == b.etParola!!.text.toString()){
                    return true
                } else {return false}
            } else {return false}
        } else {return false}
    }

    fun epostaKontrol () :Boolean {

        if (!b.etEposta.text.isNullOrEmpty()){
            if (b.etEposta.text!!.toString().length >=15) { return true }
            else { return false}

        } else {return false}
    }

    fun soyisimKontrol () : Boolean{

        if(!b.etSoyisim.text.isNullOrEmpty()){
            if (b.etSoyisim.text!!.toString().length >= 2){ return true }
            else { return false }

        } else {return false}
    }

    fun isimKontrol () :Boolean{

        if (!b.etIsim.text.isNullOrEmpty()){
            if(b.etIsim.text!!.toString().length >= 3) { return true }
            else { return false }
        } else { return false }

    }

    fun universiteKontrol () : Boolean {
        if (!b.etUniversite.text.isNullOrEmpty()){
            if(b.etUniversite.text!!.toString().length >= 3) { return true }
            else { return false }
        } else { return false }
    }

    fun bolumKontrol () : Boolean {
        if (!b.etBolum.text.isNullOrEmpty()){
            if(b.etBolum.text!!.toString().length >= 5) { return true }
            else { return false }
        } else { return false }
    }

    fun sinifKontrol () : Boolean{
        if (!b.etSinif.text.isNullOrEmpty()){
            if(b.etIsim.text!!.toString().length >= 1) { return true }
            else { return false }
        } else { return false }
    }

    fun btnUyeOlTiklandi (view:View) {

        if (isimKontrol().equals(true) &&
            soyisimKontrol().equals(true) &&
            epostaKontrol().equals(true) &&
            parolaKontrol().equals(true) &&
            universiteKontrol().equals(true) &&
            bolumKontrol().equals(true) &&
            sinifKontrol().equals(true)
        ) {

            val yuklemeAlertDialog = View.inflate(this, R.layout.yukleme_alert_dialog, null)
            val builder1 = AlertDialog.Builder(this)
            builder1.setView(yuklemeAlertDialog)
            val dialog = builder1.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(false)

            if (auth.currentUser != null) auth.signOut()
            auth.createUserWithEmailAndPassword(b.etEposta.text.toString(),b.etParola.text.toString())
                .addOnSuccessListener {

                    val ilkUyelikBilgileri = hashMapOf(
                        "İsim" to b.etIsim.text.toString() ,
                        "Soyisim" to b.etSoyisim.text.toString().uppercase() ,
                        "e-posta" to b.etEposta.text.toString(),
                        "Parola" to b.etParola.text.toString(),
                        "Üniversite" to b.etUniversite.text.toString(),
                        "Bolum" to b.etBolum.text.toString(),
                        "Sinif" to b.etSinif.text.toString(),
                        "Tahmin Skor" to "0",
                        "Üyelik Tarihi" to com.google.firebase.Timestamp(java.util.Date())
                    )

                    db.collection("Users").document(auth.currentUser!!.uid)
                        .set(ilkUyelikBilgileri)
                        .addOnSuccessListener {

                            onayMailiGonder()
                            auth.signOut()
                            dialog.dismiss()

                            Snackbar.make(view,"Üyeliğiniz oluşturuldu",Snackbar.LENGTH_INDEFINITE)
                                .setAction("Tamam") {
                                    finish()
                                }.show()
                        }
                        .addOnFailureListener {
                            dialog.dismiss()
                            Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_LONG).show()
                            finish()
                        }

                }
                .addOnFailureListener {
                    dialog.dismiss()
                    Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_LONG).show()
                    finish()
                }

        }
        else {
            Snackbar.make(view,"Bilgileri eksiksiz giriniz !",
                Snackbar.LENGTH_LONG).show()
        }
    }





}