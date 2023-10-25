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

    lateinit var recView2: RecyclerView
    lateinit var rGroup: RadioGroup
    lateinit var sPrice: RadioButton
    lateinit var sName: RadioButton

    companion object {
        var productlist = ArrayList<Product>()
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

        rGroup.setOnCheckedChangeListener { radioGroup, i ->

            val rbtn :RadioButton = findViewById(i)
            if (rbtn == sPrice) {
                productlist.sortBy { it.price }
                setDataList()
            }
            if (rbtn == sName) {
                productlist.sortBy { it.title }
                setDataList()
            }
        }


    }

    private fun setDataList() {
        val myRecAdapter2 = MyRecAdapter2(this@SecondActivity, productlist)

        recView2.adapter = myRecAdapter2
    }

    private fun getDataList() {


        val queue = Volley.newRequestQueue(this@SecondActivity)

        val url = "https://dummyjson.com/products"
// ioo
        val stringRequest = StringRequest(
            Request.Method.GET, url, { Response ->

                var prodObject = JSONObject(Response)
                var products = prodObject.getJSONArray("products")

                for (index in 0 until products.length()) {


                    val jobj = products.getJSONObject(index)
                    val imglist = ArrayList<String>()
                    val imgarr = jobj.getJSONArray("images")

                    for (x in 0 until imgarr.length()) {
                        imglist.add(imgarr.getString(x))
                    }
                    productlist.add(
                        Product(
                            jobj.getInt("id"),
                            jobj.getString("title"),
                            jobj.getString("description"),
                            jobj.getDouble("price"),
                            jobj.getDouble("rating"),
                            jobj.getString("thumbnail"),
                            imglist
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo?.isConnected == true
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
