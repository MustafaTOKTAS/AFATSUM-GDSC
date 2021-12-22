package com.toktasoft.afatsum.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toktasoft.afatsum.R
import com.toktasoft.afatsum.databinding.FragmentDialogEpostaGonderBinding
import com.toktasoft.afatsum.databinding.FragmentHakkindaBilgilendirmeBinding

class HakkindaBilgilendirmeFragment : Fragment() {

    private var _binding: FragmentHakkindaBilgilendirmeBinding? = null
    private val b get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentHakkindaBilgilendirmeBinding.inflate(inflater, container, false)
        val view = b.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val a = "Afatsum isimli bu uygulama  Google Developers Student Club'ın düzenlemiş olduğu yarışma için 21 - 23 Aralık 2021 tarihleri arasında Android Studio kullanılarak kotlin dilinde Mustafa TOKTAŞ (Toktasoft) tarafından yazılmış ve geliştirilmiştir.\n" +
                "\n" +
                "Toktasoft'un yazılı izni olmaksızın uygulama kodları üzerinde herhangi bir ekleme-çıkarma yapılması yasaktır.\nTüm hakları saklıdır©"

        b.tvBilgilendirme1.text = a

        b.ivLinkedin.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse
                    ("https://www.linkedin.com/in/toktasmustafa/")))
        }

        b.ivBugdroid.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse
                    ("https://gdsc.community.dev/events/details/developer-student-clubs-kocaeli-university-presents-bugdroid-academy/")))
        }

        b.ivSteam.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse
                    ("https://steamcommunity.com/id/mtoktas/")))
        }

    }

}