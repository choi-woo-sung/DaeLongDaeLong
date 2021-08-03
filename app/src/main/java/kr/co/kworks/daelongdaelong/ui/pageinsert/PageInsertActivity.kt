package kr.co.kworks.daelongdaelong.ui.pageinsert

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kr.co.kworks.daelongdaelong.databinding.ActivityIntroBinding
import kr.co.kworks.daelongdaelong.databinding.ActivityPageInsertBinding

class PageInsertActivity : AppCompatActivity() {


    val binding by lazy { ActivityPageInsertBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


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
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(requireActivity(), permission, 99)
    }a
}