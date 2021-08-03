package kr.co.kworks.daelongdaelong

import java.lang.reflect.Method

class Utils {

    companion object {
        var yuseong = "yuseong"
        var daedeokgu = "daedeokgu"
        var donggu = "donggu"
        var junggu = "junggu"
        var seogu = "seogu"
        var num_for = 0
        var num_mylove=0
        var num_hailey=0

        fun splitImage(imageString: String): MutableList<String> {
            var imageStringList: MutableList<String> = mutableListOf()
            imageStringList = imageString.split("\\") as MutableList<String>

            return imageStringList
        }
    }
}

