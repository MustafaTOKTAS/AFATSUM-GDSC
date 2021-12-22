package com.toktasoft.afatsum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.toktasoft.afatsum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var b:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        b= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        b.toktasoft.visibility = View.INVISIBLE

        YoYo.with(Techniques.BounceInDown)
            .duration(1000)
            .repeat(0).onEnd {

                b.toktasoft.visibility = View.VISIBLE
                YoYo.with(Techniques.BounceInUp)
                    .duration(1000)
                    .repeat(0).onEnd {
                        val intent = Intent(this,GirisYapActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .playOn(b.toktasoft)
            }
            .playOn(b.afatsum)

    }
}