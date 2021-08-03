package kr.co.kworks.daelongdaelong

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.co.kworks.daelongdaelong.IntroActivity.Companion.daeLongList
import kr.co.kworks.daelongdaelong.adapter.FoodAdapter
import kr.co.kworks.daelongdaelong.databinding.ActivityMainBinding
import kr.co.kworks.daelongdaelong.databinding.FragmentNotificationsBinding
import kr.co.kworks.daelongdaelong.ui.widescan.WideScanFragment
import kr.co.kworks.daelongdaelong.util.OnItemClick


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
    }

}


