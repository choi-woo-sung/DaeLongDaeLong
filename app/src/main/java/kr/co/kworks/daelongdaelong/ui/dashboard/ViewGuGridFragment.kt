package kr.co.kworks.daelongdaelong.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.kworks.daelongdaelong.R

import kr.co.kworks.daelongdaelong.Utils.Companion.daedeokgu
import kr.co.kworks.daelongdaelong.Utils.Companion.donggu
import kr.co.kworks.daelongdaelong.Utils.Companion.junggu
import kr.co.kworks.daelongdaelong.Utils.Companion.seogu
import kr.co.kworks.daelongdaelong.Utils.Companion.yuseong
import kr.co.kworks.daelongdaelong.databinding.FragmentViewGuGridBinding


class ViewGuGridFragment : Fragment() {

    private lateinit var binding: FragmentViewGuGridBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewGuGridBinding.inflate(inflater, container, false)



        val bundle = arguments?.getString("location").let {
            when (it) {
                yuseong -> {
                    binding.textViewGuTitle.text = getString(R.string.title_yuseong)
                }
                daedeokgu -> {
                    binding.textViewGuTitle.text = getString(R.string.title_daedeokgu)
                }
                donggu -> {
                    binding.textViewGuTitle.text = getString(R.string.title_donggu)
                }
                junggu -> {
                    binding.textViewGuTitle.text = getString(R.string.title_junggu)
                }
                seogu -> {
                    binding.textViewGuTitle.text = getString(R.string.title_seogu)
                }


            }
        }


        return binding.root

    }


}