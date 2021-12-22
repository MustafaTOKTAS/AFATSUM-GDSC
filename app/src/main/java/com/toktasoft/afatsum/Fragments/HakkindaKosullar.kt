package com.toktasoft.afatsum.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toktasoft.afatsum.R
import com.toktasoft.afatsum.databinding.FragmentHakkindaKosullarBinding
import com.toktasoft.afatsum.databinding.FragmentHakkindaKutuphanelerBinding

class HakkindaKosullarFragment : Fragment() {

    private var _binding: FragmentHakkindaKosullarBinding? = null
    private val b get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        _binding = FragmentHakkindaKosullarBinding.inflate(inflater, container, false)
        val view = b.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val a = "Hizmete erişminiz ve bu hizmetlerin kullanımı, bu koşulları kabul etmeniz ve bunlara uymanız şartına bağlıdır. Bu koşullar ziyaretçiler, kullanıcılar ve hizmete erişen veya bu hizmeti kullanan diğerleri için geçerlidir. Hizmete erişerek veya kullanarak, bu şartlara bağlı kalmayı kabul edersiniz. Şartların herhangi bir bölümüne katılmıyorsanız hizmete erişemezsiniz.\n" +
                "\n" +
                "Toktasoft'un üçüncü tarafların WEB sitelerinin veya hizmetlerinin içeriğinde, gizlilik politikaları veya uygulamaları üzerinde kontrolü yoktur ve sorumluluğu da yoktur. Ayrıca, söz konusu içeriğin, malların veya hizmetlerin kullanımında veya bu tür bir ürünün kullanımına veya bunlara bağlı olarak kullanılmasına bağlı olarak veya bunlardan kaynaklandığını iddia ettiği herhangi bir zarar veya ziyandan, Toktasoft'un doğrudan veya dolaylı olarak bu tür WEB siteleri veya servisler aracılığıyla sorumlu olmayacağını kabul ve beyan edersiniz.\n" +
                "\n" +
                "Değişiklikler\n" +
                "\n" +
                "İsteğe bağlı olarak , bu şartları herhangi bir zamanda değiştirme hakkını saklı tutarız. "

        b.tv40.text = a
    }

}