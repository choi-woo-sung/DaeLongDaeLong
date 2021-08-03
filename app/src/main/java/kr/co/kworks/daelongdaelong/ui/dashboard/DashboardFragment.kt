package kr.co.kworks.daelongdaelong.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.co.kworks.daelongdaelong.R
import kr.co.kworks.daelongdaelong.Utils
import kr.co.kworks.daelongdaelong.Utils.Companion.daedeokgu
import kr.co.kworks.daelongdaelong.Utils.Companion.donggu
import kr.co.kworks.daelongdaelong.Utils.Companion.junggu
import kr.co.kworks.daelongdaelong.Utils.Companion.num_mylove
import kr.co.kworks.daelongdaelong.Utils.Companion.seogu
import kr.co.kworks.daelongdaelong.Utils.Companion.yuseong
import kr.co.kworks.daelongdaelong.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(), View.OnClickListener {


    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            goYuseong.setOnClickListener(this@DashboardFragment)
            goDaedeokgu.setOnClickListener(this@DashboardFragment)
            goDonggu.setOnClickListener(this@DashboardFragment)
            goJunggu.setOnClickListener(this@DashboardFragment)
            goSeogu.setOnClickListener(this@DashboardFragment)
            aniCoffee.setOnClickListener(this@DashboardFragment)
        }

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.go_yuseong -> go(yuseong)
            R.id.go_daedeokgu -> go(daedeokgu)
            R.id.go_donggu -> go(donggu)
            R.id.go_junggu -> go(junggu)
            R.id.go_seogu -> go(seogu)
            R.id.ani_coffee -> {
                if (num_mylove == 10) {
                    Toast.makeText(this@DashboardFragment.context, "my love", Toast.LENGTH_SHORT).show()
                } else {
                    num_mylove++
                }
            }
        }
    }


    fun go(location: String) {
        val bundle = bundleOf("location" to location)
        findNavController().navigate(R.id.action_to_viewGuGridFragment, bundle)
    }
}