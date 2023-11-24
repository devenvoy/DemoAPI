package com.example.demoapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var recView: RecyclerView
    private val userList = ArrayList<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recView = findViewById(R.id.rec_view)

        getApiData()


    }

    private fun getApiData() {

        val queue = Volley.newRequestQueue(this@MainActivity)
        val url = "https://jsonplaceholder.typicode.com/users"

        val stringRequest = StringRequest(Request.Method.GET, url,
            {
                val jArr = JSONArray(it)

                for (index in 0..<jArr.length()) {
                    val jObj = jArr.getJSONObject(index)
                    userList.add(
                        User(
                            jObj.getInt("id"),
                            jObj.getString("name"),
                            jObj.getString("username"),
                            jObj.getString("email"),
                        )
                    )

                    val myRecAdapter = MyRecAdapter(this, userList)
                    recView.adapter = myRecAdapter
                }
            },
            {
                Log.e("E====", it.localizedMessage!!.toString())
            })

        queue.add(stringRequest)

    }

    fun next(view: View) {
        startActivity(Intent(this@MainActivity, SecondActivity::class.java))
    }

}