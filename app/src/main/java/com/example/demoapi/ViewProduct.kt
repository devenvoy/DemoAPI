package com.example.demoapi

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.demoapi.SecondActivity.Companion.productList
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class ViewProduct : AppCompatActivity() {


    private lateinit var pagerView: ViewPager
    private lateinit var title: TextView
    private lateinit var rating: TextView
    private lateinit var discription: TextView
    private lateinit var price: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        //  for full Screen
        this@ViewProduct.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_view_product)

        pagerView = findViewById(R.id.proddetail)
        title = findViewById(R.id.title)
        rating = findViewById(R.id.rating)
        discription = findViewById(R.id.discription)
        price = findViewById(R.id.price)

        val position = intent.getIntExtra("pos", 0)

        Log.d("=====", "${productList[position]}")

        val myPagerAdapter = MyPagerAdapter(this@ViewProduct, productList[position].images)

        pagerView.adapter = myPagerAdapter

        val dotsIndicator: DotsIndicator = findViewById(R.id.dots_indicator)
        dotsIndicator.setViewPager(pagerView)

        title.text = productList[position].title

        rating.text = "Rating: " + productList[position].rating.toString()

        discription.text = productList[position].description

        price.text = productList[position].price.toString() + "$"
    }
}