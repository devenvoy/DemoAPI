package com.example.demoapi

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyRecAdapter2(var secondActivity: SecondActivity, var productlist: ArrayList<Product>) :
    RecyclerView.Adapter<MyRecAdapter2.MyClass>() {

    class MyClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var price: TextView
        var rating: TextView

        init {
            image = itemView.findViewById(R.id.pimage)
            title = itemView.findViewById(R.id.ptitle)
            price = itemView.findViewById(R.id.pprice)
            rating = itemView.findViewById(R.id.prating)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyClass {
        val view = LayoutInflater.from(secondActivity).inflate(R.layout.productitems, parent, false)
        return MyClass(view)
    }

    override fun getItemCount(): Int = productlist.size

    override fun onBindViewHolder(holder: MyClass, position: Int) {

        Glide.with(secondActivity).load(productlist[position].thumbnail).into(holder.image)
        holder.title.text = productlist[position].title
        holder.price.text = productlist[position].price.toString() + "$"
        holder.rating.text = "Rating :" + productlist[position].rating.toString()

        holder.image.setOnClickListener {
            secondActivity.startActivity(
                Intent(
                    secondActivity,
                    ViewProduct::class.java
                ).putExtra("pos", position)
            )
        }

    }
}
