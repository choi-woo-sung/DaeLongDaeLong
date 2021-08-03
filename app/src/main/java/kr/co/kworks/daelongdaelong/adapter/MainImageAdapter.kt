package kr.co.kworks.daelongdaelong.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import kr.co.kworks.daelongdaelong.databinding.ItemFoodviewBinding
import kr.co.kworks.daelongdaelong.databinding.ItemMainBinding
import kr.co.kworks.daelongdaelong.vo.DaeLongVo

class MainImageAdapter(var list: MutableList<String>) :
    RecyclerView.Adapter<MainImageAdapter.Holder>() {


    class Holder(val binding: ItemFoodviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //        lateinit var currentDaeLong: DaeLongVo
        val storage = FirebaseStorage.getInstance("gs://daelongdaelong-36492.appspot.com")
        val storageRef = storage.reference


        fun setDaeLong(imagViewUrl: String) {

            val circularProgressDrawable = CircularProgressDrawable(binding.imageView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            with(binding) {
                storageRef.child(imagViewUrl).downloadUrl
                    .addOnSuccessListener { uri -> //이미지 로드 성공시
                        Glide.with(imageView.context.applicationContext)
                            .load(uri)
                            .placeholder(circularProgressDrawable)
                            .into(imageView)
                    }.addOnFailureListener { //이미지 로드 실패시
                        Toast.makeText(
                            imageView.context.applicationContext,
                            "실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainImageAdapter.Holder {
        val binding =
            ItemFoodviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list.get(position)
        holder.setDaeLong(data)

    }

}