package com.toktasoft.afatsum.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.toktasoft.afatsum.Fragments.HakkindaBilgilendirmeFragment
import com.toktasoft.afatsum.Fragments.HakkindaKosullarFragment
import com.toktasoft.afatsum.Fragments.HakkindaKutuphanelerFragment

class HakkindaViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    val gosterilecekFragmentlerListesi = arrayListOf<Fragment>(
        HakkindaBilgilendirmeFragment(),
        HakkindaKutuphanelerFragment(),
        HakkindaKosullarFragment()
    )


    override fun getItemCount(): Int {

        return gosterilecekFragmentlerListesi.size
    }

    override fun createFragment(position: Int): Fragment {

        return gosterilecekFragmentlerListesi[position]

    }

    fun fragmentEkle (fragment : Fragment) {
        gosterilecekFragmentlerListesi.add(fragment)
    }
}