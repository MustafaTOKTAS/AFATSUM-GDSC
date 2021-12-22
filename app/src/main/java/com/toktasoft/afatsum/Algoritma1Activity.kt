package com.toktasoft.afatsum

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.toktasoft.afatsum.databinding.ActivityAlgoritma1Binding

class Algoritma1Activity : AppCompatActivity() {

    lateinit var b:ActivityAlgoritma1Binding

    override fun onCreate(savedInstanceState: Bundle?) {

        b= ActivityAlgoritma1Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        setSupportActionBar(b.toolbarAlgoritma)
        toolbarAyarla()
    }



    fun btnAlgoritma1IsterlerTiklandi (view: View) {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Bir banka hesabı için bir şifre belirlenmek isteniyor. Ancak, parola biçiminde üç kısıtlama var:\n" +
                "\n" +
                "Yalnızca alfasayısal karakterler içermelidir ( a - z , A - Z , 0 - 9 );\n" +
                "çift sayıda harf olmalıdır (2-4-6 tane harf);\n" +
                "tek sayıda rakam olmalıdır(1-3-5-7 tane rakam).\n" +
                "\n" +
                "N karakterden oluşan bir S dizisi yazılır. S dizisi , boşluklara bölünerek ve boşluklar kaldırılarak kelimelere bölünebilir.\n" +
                "Amaç, geçerli şifrelerden en uzun olanını seçip uzunluğunun kullanıcıya bildirilmesidir.\n" +
                "\n" +
                "Örneğin, \"test 5 a0A pass007 ?xy1\" verildiğinde , beş kelime vardır ve bunlardan ikisi geçerli paroladır:\n" +
                "\"a0A\" ve \"pass007\".  Bu nedenle en uzun parola \"pass007\" ve uzunluğu 7'dir. Ne \"test\" ne  \"?xy1\" ne de \"5\"\n" +
                "geçerli bir parola değildir, çünkü \"?\" bir alfasayısal karakter değildir, \"test\" rakam içermemektedir ve \"5\" harf içermemektedir.\n" +
                "\n" +
                "Yazılan algoritma:\n" +
                "\n" +
                "Fonksiyon, N karakterden oluşan boş olmayan bir S dizisi verildiğinde, geçerli bir parola olan dizeden en uzun kelimenin uzunluğunu döndürür. Böyle bir kelime yoksa -1 döndürür.\n" +
                "\n" +
                "Örneğin, S = \"test 5 a0A pass007 ?xy1\" girildiğinde , fonksiyon yukarıda açıklandığı gibi 7 rakamını döndürür.")
        builder.setPositiveButton("Kapat",{
            dialogInterface: DialogInterface , i:Int ->
        })
        builder.show()
    }

    fun toolbarAyarla() {

        setSupportActionBar(b.toolbarAlgoritma)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_32)
        b.toolbarAlgoritma.title = "Algoritma 1"
        b.toolbarAlgoritma.setNavigationOnClickListener {
            finish()
        }
    }

    fun btnCiktiHesaplaTiklandi (view:View){

        var girilenDizi = girdiyiAl()

        val dizi = girilenDizi.split(" ")
        var gecerliParolalar = ArrayList<String>()

        for (eleman in dizi){

            if (alfaSayisalKarakerKontrolu(eleman) == 1){
                val harf = harfSayisiBulucu(eleman)
                val rakam = rakamSayisiBulucu(eleman)
                if (rakam == "tek" && harf == "çift"){
                    gecerliParolalar.add(eleman)
                }
            }

        }

        if (gecerliParolalar.size != 0){
            var i = 0
            var enBuyuk = gecerliParolalar[0].length
            while (i < gecerliParolalar.size){
                var num = gecerliParolalar[i].length
                if (enBuyuk < num) enBuyuk = num
                i++
            }
            b.tvCikti.text = enBuyuk.toString()
        }

        else{
            b.tvCikti.text = "-1"
        }

    }

    fun girdiyiAl() : String{

        var a = b.etDiziGirdisi.text.toString()
        a = a?.trim()
        if (a.isNullOrEmpty()){
            return "Hata"
        }
        else return a
    }

    fun harfSayisiBulucu (gelenDizi : String) :String {

        val harfDizi = gelenDizi.replace("[^a-zA-Z]".toRegex(),"")

        if (!harfDizi.isNullOrEmpty()){
            val uzunluk = harfDizi.length
            val kalan = uzunluk%2
            if (kalan != 0){
                return "tek"
            }
            else return "çift"
        }
        else return "harfYok"
    }

    fun rakamSayisiBulucu (gelenDizi : String) :String {

        var rakamDizi = gelenDizi.replace("[^0-9]".toRegex(),"")

        if (!rakamDizi.isNullOrEmpty()){

            var a = rakamDizi.toInt()
            var i = 0
            while (a != 0) {
                a /= 10
                ++i
            }
            val kalan = i%2
            if (kalan != 0){
                return "tek"
            }
            else return "çift"
        }
        else return "rakamYok"
    }

    fun alfaSayisalKarakerKontrolu (gelen :String) : Int {

        val a = gelen.length
        val regex = Regex("[^A-Za-z0-9]")
        val arindirilmisGelen = regex.replace(gelen,"")
        val b = arindirilmisGelen.length
        if (a!=b){
            return 0
        }
        else return 1
    }
}