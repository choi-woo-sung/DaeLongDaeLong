package kr.co.kworks.daelongdaelong.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import kr.co.kworks.daelongdaelong.IntroActivity.Companion.daeLongList
import kr.co.kworks.daelongdaelong.Utils
import kr.co.kworks.daelongdaelong.Utils.Companion.num_for
import kr.co.kworks.daelongdaelong.adapter.MainAdapter
import kr.co.kworks.daelongdaelong.adapter.MainImageAdapter
import kr.co.kworks.daelongdaelong.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mainImageAdapter: MainImageAdapter

    /**
     * Indicator 포지션숫자
     */
    private var indicator_num: Int = 0

    private var forHailey: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val mainAdapter = MainAdapter(daeLongList)
            viewPagerMain.adapter = mainAdapter
            indicator.setViewPager(viewPagerMain);
            viewPagerMain.registerOnPageChangeCallback(pageChangeListner)

            pageCurlView.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.indicator.animatePageSelected(position % indicator_num);
                }
            })

            aniCoffee.setOnClickListener {
                if (num_for == 10) {
                    Toast.makeText(activity, "for", Toast.LENGTH_SHORT).show()
                } else {
                    num_for++
                }
            }


        }
    }

    val pageChangeListner = object : OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            with(binding) {
                mainImageAdapter = MainImageAdapter(daeLongList.get(position).imageList!!)
                pageCurlView.adapter = mainImageAdapter
                mainImageAdapter.notifyDataSetChanged()
                indicator.removeAllViews()
                daeLongList.get(position).imageList?.let {
                    indicator.createIndicators(it.size, 0);
                    indicator_num = it.size
                }
            }
        }
    }

}