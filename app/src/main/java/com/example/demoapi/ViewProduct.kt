package com.example.demoapi

import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.demoapi.SecondActivity.Companion.productlist
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class ViewProduct : AppCompatActivity() {


    lateinit var pagerview: ViewPager
    lateinit var title: TextView
    lateinit var rating: TextView
    lateinit var discription: TextView
    lateinit var price: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_product)

        pagerview = findViewById(R.id.proddetail)
        title = findViewById(R.id.title)
        rating = findViewById(R.id.rating)
        discription = findViewById(R.id.discription)
        price = findViewById(R.id.price)

        var position = intent.getIntExtra("pos", 0)


        Log.d("=====", "${productlist[position]}")

        var myPagerAdapter = MyPagerAdapter(this@ViewProduct, productlist[position].images)

        pagerview.adapter = myPagerAdapter


        val dotsIndicator: DotsIndicator = findViewById(R.id.dots_indicator)
        dotsIndicator.setViewPager(pagerview)

        title.text = productlist[position].title

        rating.text = "Rating: " + productlist[position].rating.toString()

        discription.text = productlist[position].description

        price.text = productlist[position].price.toString() + "$"
    }
}