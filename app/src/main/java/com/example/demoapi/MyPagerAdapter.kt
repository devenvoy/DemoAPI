package com.example.demoapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide

class MyPagerAdapter(var viewProduct: ViewProduct, var images: ArrayList<String>) : PagerAdapter() {

    override fun getCount(): Int = images.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var view = LayoutInflater.from(viewProduct).inflate(R.layout.pimage, container, false)

        var imagee: ImageView = view.findViewById(R.id.imageee)

        Glide.with(viewProduct).load(images[position]).into(imagee)

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}
