package kr.co.kworks.daelongdaelong.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.google.firebase.storage.FirebaseStorage
import kr.co.kworks.daelongdaelong.R
import kr.co.kworks.daelongdaelong.databinding.ItemMainBinding
import kr.co.kworks.daelongdaelong.util.OnItemClick
import kr.co.kworks.daelongdaelong.vo.DaeLongVo

class MainAdapter(var list: MutableList<DaeLongVo>) :

    RecyclerView.Adapter<MainAdapter.Holder>() {


    class Holder(val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        lateinit var currentDaeLong: DaeLongVo



        fun setDaeLong(daeLong: DaeLongVo) {
            currentDaeLong = daeLong

            with(binding) {
                textViewMainTitle.text = daeLong.title
                textViewMainReview.text = daeLong.review?.replace("\\n", "\n")

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.Holder {
        val binding =
            ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list.get(position)
        holder.setDaeLong(data)
    }

}
