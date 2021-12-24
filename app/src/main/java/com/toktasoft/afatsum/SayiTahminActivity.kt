package com.toktasoft.afatsum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.toktasoft.afatsum.databinding.ActivitySayiTahminBinding

class SayiTahminActivity : AppCompatActivity() {

    private lateinit var b:ActivitySayiTahminBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    var tutulanSayi :Int = -255
    var kalanHak : Int =0
    var kontrol = false
    var skor =0

    override fun onCreate(savedInstanceState: Bundle?) {

        b= ActivitySayiTahminBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        auth= Firebase.auth
        db = Firebase.firestore

        db.collection("Users").document(auth.currentUser!!.uid).get().addOnSuccessListener {
            skor = it.getString("Tahmin Skor")!!.toInt()
            b.tvSkor.text = skor.toString()


        }
        .addOnFailureListener {
            Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_LONG).show()
            b.tvSkor.text = "HATA"
        }

        setSupportActionBar(b.toolbarTahmin)
        toolbarAyarla()
    }


    fun toolbarAyarla() {

        setSupportActionBar(b.toolbarTahmin)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_32)
        b.toolbarTahmin.title = "Sayı Tahmin Oyunu"
        b.toolbarTahmin.setNavigationOnClickListener {
            finish()
        }
    }

    fun btnSayiTutTiklandi (view: View){

        kontrol = true
        kalanHak =4
        b.tvKalanHak.text = kalanHak.toString()

        val rnd = (1..10).random()
        when (rnd){

            1 ->{
                tutulanSayi = (1..10).random()
                b.tvBildirim.text = "1..10 arasında sayı tutuldu, sınır değerler dahil"
            }
            2 -> {
                tutulanSayi = (11..20).random()
                b.tvBildirim.text = "11..20 arasında sayı tutuldu, sınır değerler dahil"
            }
            3 -> {
                tutulanSayi = (22..30).random()
                b.tvBildirim.text = "22..30 arasında sayı tutuldu, sınır değerler dahil"
            }
            4 -> {
                tutulanSayi = (31..40).random()
                b.tvBildirim.text = "31..40 arasında sayı tutuldu, sınır değerler dahil"
            }
            5 -> {
                tutulanSayi = (41..50).random()
                b.tvBildirim.text = "41..50 arasında sayı tutuldu, sınır değerler dahil"
            }
            6 -> {
                tutulanSayi = (51..60).random()
                b.tvBildirim.text = "51..60 arasında sayı tutuldu, sınır değerler dahil"
            }
            7 -> {
                tutulanSayi = (61..70).random()
                b.tvBildirim.text = "61..70 arasında sayı tutuldu, sınır değerler dahil"
            }
            8 -> {
                tutulanSayi = (71..80).random()
                b.tvBildirim.text = "71..80 arasında sayı tutuldu, sınır değerler dahil"
            }
            9 -> {
                tutulanSayi = (81..90).random()
                b.tvBildirim.text = "81..90 arasında sayı tutuldu, sınır değerler dahil"
            }
            10 -> {
                tutulanSayi = (91..100).random()
                b.tvBildirim.text = "91..100 arasında sayı tutuldu, sınır değerler dahil"
            }
        }

    }

    fun btnTahminEtTiklandi (view:View){

        if ( kontrol == true){

            if (kalanHak > 0 ){

                if (!b.etTahmin.text.isNullOrEmpty()){

                    val a = b.etTahmin.text.toString().replace("[^0-9]".toRegex(),"").toInt()

                    if (a == tutulanSayi){

                        b.tvTahminSonucuBildir.text = "TEBRİKLER !! Doğru Tahmin :D"
                        b.tvBildirim.text =""
                        kontrol =false
                        b.tvKalanHak.text = "Hak Sayısı"
                        skor = skor +1
                        b.tvSkor.text = skor.toString()

                    }
                    else if (a < tutulanSayi){

                        b.tvTahminSonucuBildir.text = "Tahminin küçük"
                         kalanHak = kalanHak - 1
                        b.tvKalanHak.text = kalanHak.toString()
                        b.etTahmin.setText("")
                    }
                    else {
                        b.tvTahminSonucuBildir.text = "Tahminin büyük"
                        kalanHak = kalanHak - 1
                        b.tvKalanHak.text = kalanHak.toString()
                        b.etTahmin.setText("")
                    }

                }
                else  b.tvTahminSonucuBildir.text = "Lütfen Sayı Giriniz"
            }
            else b.tvTahminSonucuBildir.text = "Tahmin Hakkınız Kalmadı"

        }
        else b.tvTahminSonucuBildir.text = "Öncelikle \"SAYI TUT\" butonuna basman gerek :D"

    }

    override fun onStop() {
        super.onStop()
        db.collection("Users").document(auth.currentUser!!.uid).update(mapOf(
            "Tahmin Skor" to skor.toString()
        ))
    }

}