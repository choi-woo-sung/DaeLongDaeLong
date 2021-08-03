package kr.co.kworks.daelongdaelong

import android.R
import android.animation.Animator
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kr.co.kworks.daelongdaelong.Utils.Companion.splitImage
import kr.co.kworks.daelongdaelong.databinding.ActivityIntroBinding
import kr.co.kworks.daelongdaelong.vo.DaeLongVo


class IntroActivity : AppCompatActivity() {

    val binding by lazy { ActivityIntroBinding.inflate(layoutInflater) }

    companion object {
        lateinit var daeLongList: MutableList<DaeLongVo>
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        daeLongList = mutableListOf()
        val db = Firebase.firestore
        val docRef = db.collection("DaeLong")


        docRef.orderBy("date", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    val gson = Gson()
                    val jsonElement = gson.toJsonTree(document.data)
                    var daLongVo: DaeLongVo = gson.fromJson(jsonElement, DaeLongVo::class.java)

                    daLongVo.image?.let {
                        val imageStringList: MutableList<String> = splitImage(it)
                        daLongVo.imageList = imageStringList
                    }

                    daeLongList.add(daLongVo)
                }

            }.addOnCompleteListener {

            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }




        binding.aniCoffee.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }


            override fun onAnimationEnd(animation: Animator?) {

                val intent = Intent(this@IntroActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }


        })


    }


}