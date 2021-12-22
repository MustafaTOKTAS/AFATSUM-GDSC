package com.toktasoft.afatsum.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toktasoft.afatsum.Uyeler
import com.toktasoft.afatsum.databinding.RecyclerViewLayoutBinding

class UyelerAdapter (private val uyeList: ArrayList<Uyeler>): RecyclerView.Adapter<UyelerAdapter.UyeTutucu> () {

    class UyeTutucu (val b : RecyclerViewLayoutBinding) : RecyclerView.ViewHolder (b.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UyeTutucu {
        val b = RecyclerViewLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UyeTutucu(b)
    }

    override fun onBindViewHolder(h: UyeTutucu, position: Int) {
        val a = uyeList.get(position).isim + " " + uyeList.get(position).soyisim
        h.b.tvIsimSoyisim.text = a
        h.b.tvUniversite2.text = uyeList.get(position).universite
        h.b.tvSkor2.text = uyeList.get(position).skor

    }

    override fun getItemCount(): Int {
        return uyeList.size
    }
}