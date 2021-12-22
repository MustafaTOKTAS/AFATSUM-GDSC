package com.toktasoft.afatsum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.toktasoft.afatsum.Adapters.UyelerAdapter
import com.toktasoft.afatsum.databinding.ActivityUyelerBinding

class UyelerActivity : AppCompatActivity() {

    private lateinit var b: ActivityUyelerBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var uyelerArrayList : ArrayList<Uyeler>
    private lateinit var adapter :UyelerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        b= ActivityUyelerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        auth = Firebase.auth
        db = Firebase.firestore

        uyelerArrayList = ArrayList<Uyeler>()
        uyeleriCek()
        b.rv.layoutManager = LinearLayoutManager(this)
        adapter = UyelerAdapter(uyelerArrayList)
        b.rv.adapter = adapter

        setSupportActionBar(b.toolbarUyeler)
        toolbarAyarla()

    }


    fun toolbarAyarla() {

        setSupportActionBar(b.toolbarUyeler)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_32)
        b.toolbarUyeler.title = "Üyeler"
        b.toolbarUyeler.setNavigationOnClickListener {
            finish()
        }
    }

    fun uyeleriCek(){

        db.collection("Users").orderBy("Tahmin Skor", Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if (error != null){
                Toast.makeText(applicationContext,error.localizedMessage,Toast.LENGTH_LONG).show()
            }
            else {
                if ( value != null){
                    if (!value.isEmpty){

                        val dokumanlar = value.documents
                        uyelerArrayList.clear()
                        for (dokuman in dokumanlar){
                            val isim = dokuman.getString("İsim")
                            val soyisim = dokuman.getString("Soyisim")
                            val universite = dokuman.getString("Üniversite")
                            val skor = dokuman.getString("Tahmin Skor")

                            val uyeler  = Uyeler(isim,soyisim,universite,skor)
                            uyelerArrayList.add(uyeler)
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }

    }


}