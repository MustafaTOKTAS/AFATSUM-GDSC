package com.toktasoft.afatsum

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.toktasoft.afatsum.databinding.ActivityAnasayfaBinding

class AnasayfaActivity : AppCompatActivity() {

    private lateinit var b:ActivityAnasayfaBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {

        b= ActivityAnasayfaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(b.root)


        setSupportActionBar(b.toolbarAnasayfa)
        auth= Firebase.auth
        db = Firebase.firestore

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.anasayfa_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val secilenItem = item.itemId
        when (secilenItem) {

            R.id.idUygulamaHakkinda -> startActivity(Intent(this,UygulamaHakkindaActivity::class.java))

            R.id.idProfilim ->  startActivity(Intent(this,ProfilActivity::class.java))
        }
        return true
    }

    fun btnalgoritmaTiklandi (view: View){

        startActivity(Intent(this,Algoritma1Activity::class.java))
    }

    fun btnSayiTahminOyunuTiklandi (view:View){

        startActivity(Intent(this,SayiTahminActivity::class.java))
    }


    fun btnUyeleriGorTiklandi (view: View){
        startActivity(Intent(this,UyelerActivity::class.java))

    }
}