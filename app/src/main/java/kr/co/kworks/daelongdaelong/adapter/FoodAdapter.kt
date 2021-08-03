package kr.co.kworks.daelongdaelong.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import kr.co.kworks.daelongdaelong.MainActivity
import kr.co.kworks.daelongdaelong.Utils.Companion.splitImage
import kr.co.kworks.daelongdaelong.databinding.ItemFoodBinding
import kr.co.kworks.daelongdaelong.util.OnItemClick
import kr.co.kworks.daelongdaelong.vo.DaeLongVo


class FoodAdapter(val list: MutableList<DaeLongVo>, val mcallback: OnItemClick) :
    RecyclerView.Adapter<FoodAdapter.Holder>() {


    class Holder(val binding: ItemFoodBinding, val mcallback: OnItemClick) :
        RecyclerView.ViewHolder(binding.root) {
        lateinit var currentDaeLong: DaeLongVo
        val storage = FirebaseStorage.getInstance("gs://daelongdaelong-36492.appspot.com")
        val storageRef = storage.reference


        init {
            binding.root.setOnClickListener {
                mcallback.onClick(currentDaeLong.latitude!!, currentDaeLong.longitude!!)

            }
        }

        fun setDaeLong(daeLong: DaeLongVo) {
            currentDaeLong = daeLong
            with(binding) {
                textViewTitle.text = "${daeLong.title}"
                textViewContent.text = daeLong.content?.replace("\\n", "\n")

                currentDaeLong.imageList?.get(0)?.let {
                    storageRef.child(it).downloadUrl
                        .addOnSuccessListener { uri -> //이미지 로드 성공시
                            Glide.with(textViewContent.context.applicationContext)
                                .load(uri)
                                .into(imageViewFood)
                        }.addOnFailureListener { //이미지 로드 실패시
                            Toast.makeText(
                                textViewContent.context.applicationContext,
                                "실패",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }

        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.Holder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, mcallback)
    }


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list.get(position)
        holder.setDaeLong(data)
    }
}