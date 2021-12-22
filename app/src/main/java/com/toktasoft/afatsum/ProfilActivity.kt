package com.toktasoft.afatsum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.toktasoft.afatsum.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {

    private lateinit var b:ActivityProfilBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {

        b= ActivityProfilBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        setSupportActionBar(b.toolbarProfil)
        auth= Firebase.auth
        db = Firebase.firestore

        toolbarAyarla()
        aktifKullaniciVerileriniCek()


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profil_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val secilenItem = item.itemId
        when (secilenItem) {

            R.id.idDegisiklikleriKaydet -> {


                if (isimKontrol().equals(true) &&
                    soyisimKontrol().equals(true) &&
                    universiteKontrol().equals(true) &&
                    bolumKontrol().equals(true) &&
                    sinifKontrol().equals(true)
                ) {

                    db.collection("Users").document(auth.currentUser!!.uid).update(
                        mapOf(
                            "İsim" to b.etIsim.text.toString(),
                            "Soyisim" to b.etSoyisim.text.toString(),
                            "Üniversite" to b.etUniversite.text.toString(),
                            "Bolum" to b.etBolum.text.toString(),
                            "Sinif" to b.etSinif.text.toString()
                        )
                    ).addOnSuccessListener {

                        Toast.makeText(this,"Değişiklikler Kaydedildi !",Toast.LENGTH_LONG).show()

                    }.addOnFailureListener {

                        Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
                    }

                    aktifKullaniciVerileriniCek()
                }
                else {
                    Toast.makeText(this,"Alanlar eksiksiz ve doğru doldurulmalıdır !",Toast.LENGTH_LONG).show()
                }


            }
            R.id.idCikisYap -> {
                auth.signOut()
                val intent = Intent(this,GirisYapActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }
        return true
    }

    fun toolbarAyarla () {
        setSupportActionBar(b.toolbarProfil)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_32)
        b.toolbarProfil.title = "Profilim"
        b.toolbarProfil.setNavigationOnClickListener {
            finish()
        }
    }

    fun aktifKullaniciVerileriniCek(){
        db.collection("Users").document(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                b.etIsim.setText(it.getString("İsim"))
                b.etSoyisim.setText(it.getString("Soyisim"))
                b.etEposta.setText(it.getString("e-posta"))
                b.etUniversite.setText(it.getString("Üniversite"))
                b.etBolum.setText(it.getString("Bolum"))
                b.etSinif.setText(it.getString("Sinif"))
            }

    }

    fun btnParolaDegistirTiklandi (view: View){
        if (!b.etYeniParola.text.isNullOrEmpty() || !b.etYeniParolaTekrar.text.isNullOrEmpty() ){
            if (b.etYeniParola.text!!.length >= 6 && b.etYeniParola.text.toString() == b.etYeniParolaTekrar.text.toString() ){

                auth.currentUser!!.updatePassword(b.etYeniParola.toString())
                .addOnSuccessListener {

                    db.collection("Users").document(auth.currentUser!!.uid).update( mapOf(
                        "Parola" to b.etYeniParola.text.toString()
                    ))
                    Toast.makeText(this,"Parola Değiştirildi !",Toast.LENGTH_LONG).show()
                    b.etYeniParola.setText("")
                    b.etYeniParolaTekrar.setText("")


                }.addOnFailureListener {
                        Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
            else Toast.makeText(this,"Parolalar birbiri ile eşleşmemektedir !",Toast.LENGTH_LONG).show()
        }
        else Toast.makeText(this,"Alanlar eksiksiz ve doğru doldurulmalıdır !",Toast.LENGTH_LONG).show()

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



}