package com.example.demoapi

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class SecondActivity : AppCompatActivity() {

    private lateinit var recView2: RecyclerView
    private lateinit var rGroup: RadioGroup
    private lateinit var sPrice: RadioButton
    private lateinit var sName: RadioButton

    companion object {
        var productList = ArrayList<Product>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if (!isInternetAvailable()) {
            showInternetDialog()
        }

        recView2 = findViewById(R.id.rec_view2)
        rGroup = findViewById(R.id.rgroup)
        sPrice = findViewById(R.id.pricesort)
        sName = findViewById(R.id.namesort)

        getDataList()
        setDataList()

        rGroup.setOnCheckedChangeListener { _, i ->

            val rBtn: RadioButton = findViewById(i)
            if (rBtn == sPrice) {
                productList.sortBy { it.price }
                setDataList()
            }
            if (rBtn == sName) {
                productList.sortBy { it.title }
                setDataList()
            }
        }


    }

    private fun setDataList() {
        val myRecAdapter2 = MyRecAdapter2(this@SecondActivity, productList)

        recView2.adapter = myRecAdapter2
    }

    private fun getDataList() {


        val queue = Volley.newRequestQueue(this@SecondActivity)

        val url = "https://dummyjson.com/products"
// ioo
        val stringRequest = StringRequest(
            Request.Method.GET, url, { response ->

                val prodObject = JSONObject(response)
                val products = prodObject.getJSONArray("products")

                for (index in 0 ..<  products.length()) {

                    val jObj = products.getJSONObject(index)
                    val imgList = ArrayList<String>()
                    val arrImg = jObj.getJSONArray("images")

                    for (x in 0..< arrImg.length()) {
                        imgList.add(arrImg.getString(x))
                    }
                    productList.add(
                        Product(
                            jObj.getInt("id"),
                            jObj.getString("title"),
                            jObj.getString("description"),
                            jObj.getDouble("price"),
                            jObj.getDouble("rating"),
                            jObj.getString("thumbnail"),
                            imgList
                        )
                    )
                }
                setDataList()
            }, {

            })

        queue.add(stringRequest)
    }


    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected == true
        }
    }

    private fun showInternetDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Internet Connection")
        builder.setMessage("Please turn on your internet connection to use this app.")
        builder.setPositiveButton("OK") { _, _ ->
            if (!isInternetAvailable()) {
                showInternetDialog()
            } else {
                startActivity(Intent(this@SecondActivity, SecondActivity::class.java))
                finish()
            }
        }

        val dialog = builder.create()
        dialog.show()
    }
}
