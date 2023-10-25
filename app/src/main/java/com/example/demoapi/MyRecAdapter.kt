package com.example.demoapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


// extends to RecyclerView adapter with our class that resides in our main Adapter class
class MyRecAdapter(var mainActivity: MainActivity, var userList: ArrayList<User>) :
    RecyclerView.Adapter<MyRecAdapter.Myclass>() {


    // Class holds the  View that has to be craated
    class Myclass(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name : TextView
        var username : TextView
        var email : TextView
        // our view's objects connect in init block with id in view layout
        init {
            name  = itemView.findViewById(R.id.name)
            username  = itemView.findViewById(R.id.username)
            email  = itemView.findViewById(R.id.email)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myclass {
        var view = LayoutInflater.from(mainActivity).inflate(R.layout.myitem, parent, false)
        return Myclass(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: Myclass, position: Int) {
        holder.name.text = userList[position].name
        holder.username.text = userList[position].username
        holder.email.text = userList[position].email
    }

}
