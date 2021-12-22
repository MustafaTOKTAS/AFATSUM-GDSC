package com.toktasoft.afatsum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.toktasoft.afatsum.Adapters.HakkindaViewPagerAdapter
import com.toktasoft.afatsum.databinding.ActivityUygulamaHakkindaBinding

class UygulamaHakkindaActivity : AppCompatActivity() {

    private lateinit var b: ActivityUygulamaHakkindaBinding
    val baslikListesi = arrayListOf<String>("Bilgilendirme", "Kütüphaneler", "Kullanım ve Gizlilik Koşulları")


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        b = ActivityUygulamaHakkindaBinding.inflate(layoutInflater)
        setContentView(b.root)

        toolbarAyarla()
        sekmeleriAyarla()
    }


    fun toolbarAyarla() {

        setSupportActionBar(b.toolbarHakkinda)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_32)
        b.toolbarHakkinda.setNavigationOnClickListener {
            finish()
        }
    }

    fun sekmeleriAyarla() {

        val adapter = HakkindaViewPagerAdapter(this)
        b.viewPAger.adapter = adapter

        TabLayoutMediator(b.tabLayout, b.viewPAger) { tab, position ->
            tab.setText(baslikListesi[position])
        }.attach()

    }
}