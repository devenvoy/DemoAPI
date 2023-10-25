package com.example.demoapi

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String
)

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price : Double,
    val rating: Double,
    val thumbnail: String,
    val images: ArrayList<String>
)
