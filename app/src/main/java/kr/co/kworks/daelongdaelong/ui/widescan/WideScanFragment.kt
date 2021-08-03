package kr.co.kworks.daelongdaelong.ui.widescan

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import kr.co.kworks.daelongdaelong.IntroActivity.Companion.daeLongList
import kr.co.kworks.daelongdaelong.MainActivity
import kr.co.kworks.daelongdaelong.R
import kr.co.kworks.daelongdaelong.Utils
import kr.co.kworks.daelongdaelong.Utils.Companion.num_for
import kr.co.kworks.daelongdaelong.Utils.Companion.num_hailey
import kr.co.kworks.daelongdaelong.Utils.Companion.num_mylove
import kr.co.kworks.daelongdaelong.adapter.FoodAdapter
import kr.co.kworks.daelongdaelong.databinding.FragmentNotificationsBinding
import kr.co.kworks.daelongdaelong.util.OnItemClick
import kr.co.kworks.daelongdaelong.vo.NaverItem
import ted.gun0912.clustering.naver.TedNaverClustering

class WideScanFragment : Fragment(), OnMapReadyCallback, OnItemClick {


    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var mnaverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (!locationSource.isActivated) { // 권한 거부됨
            mnaverMap.locationTrackingMode = LocationTrackingMode.None
        }
        return
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        checkPermisson()
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.drawer.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED)
        binding.buttonHamburger.setOnClickListener(listener)
        binding.aniCoffee.setOnClickListener(listener)

        val foodAdapter = FoodAdapter(daeLongList, this)
        binding.recyclerviewDaelong.adapter = foodAdapter
        binding.recyclerviewDaelong.layoutManager = LinearLayoutManager(this.context)

        val fm = childFragmentManager
        val mapFragment = fm?.let {
            it.findFragmentById(R.id.map_fragment) as MapFragment?
                ?: MapFragment.newInstance().also {
                    fm.beginTransaction().add(R.id.map_fragment, it).commit()
                }
        }

        mapFragment.getMapAsync(this)


    }

    val listener = View.OnClickListener { v ->
        when (v!!.id) {
            R.id.button_hamburger -> {
                openHamburger()
            }
            R.id.ani_coffee -> {
                if (num_hailey == 10 && num_mylove == 10 && num_for == 10) {
                    Toast.makeText(this@WideScanFragment.context, "Hailey", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    Utils.num_hailey++
                }
            }
        }

    }

    override fun onMapReady(naverMap: NaverMap) {
        mnaverMap = naverMap
        mnaverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        this.context?.let {
            TedNaverClustering.with<NaverItem>(it, mnaverMap)
                .customMarker { clusterItem ->
                    Marker(clusterItem.position).apply {
                        icon = MarkerIcons.GREEN
//                        this.title = clusterItem.position.latitude.toString()
                    }

                }
//                .customCluster {
//                    TextView(this.context).apply {
//                        setBackgroundColor(Color.GREEN)
//                        setTextColor(Color.WHITE)
//                        text = "${it.size}개"
//                        setPadding(10, 10, 10, 10)
//                    }
//                }
                .items(getItems())
                .make()
        }

    }

    private fun getItems(): List<NaverItem> {
//        val bounds = mnaverMap.contentBounds
        return ArrayList<NaverItem>().apply {
            for (index in daeLongList.iterator()) {
                val temp = NaverItem(
//                    (bounds.northLatitude - bounds.southLatitude) * Math.random() + bounds.southLatitude,
//                    (bounds.eastLongitude - bounds.westLongitude) * Math.random() + bounds.westLongitude
                    index.latitude!!,
                    index.longitude!!
                )
                add(temp)
            }
        }

    }


    override fun onClick(latitude: Double, longitude: Double) {
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(latitude, longitude))
        mnaverMap.moveCamera(cameraUpdate)
        binding.drawer.closeDrawer(
            Gravity.LEFT
        )
    }

    fun openHamburger() {

        with(binding.drawer) {
            openDrawer(Gravity.LEFT)
        }


    }


    fun checkPermisson() {
        context?.let {
            val cameraPermission =
                ContextCompat.checkSelfPermission(it, android.Manifest.permission.CAMERA)
            if (cameraPermission == PackageManager.PERMISSION_GRANTED) {

            } else {
                //권한이 허락되있지않으니까 확인!
                requestPemmsion()
            }
        }

    }

    fun requestPemmsion() {
        val permission = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        ActivityCompat.requestPermissions(requireActivity(), permission, 99)
    }

}